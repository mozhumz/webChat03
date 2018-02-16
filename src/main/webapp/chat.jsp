<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>聊天页面</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	//获取servlet session中的name属性的值
	var username='${username}';
	console.log(username);
	var ws;//一个通讯管道
	//target为服务器端endpoint的访问地址
	var target="ws://localhost:8081/webChat03/chat?username=${username}?password=${password}";
	window.onload=function(){
		//进入聊天页面以后就打开socket通道
		//浏览器兼容性判断
		 if ('WebSocket' in window) {
             ws = new WebSocket(target);
         } else if ('MozWebSocket' in window) {
             ws = new MozWebSocket(target);
         } else {
             alert('WebSocket is not supported by this browser.');
             return;
         }
		//获取服务器转发过来的JSON消息
		ws.onmessage=function(event){
			//console.log(event);
			//jquery解析json
			eval("var msg="+event.data);
			//console.log(msg);
			//显示某人进入聊天室
			if(msg.alert){
				$('#content').append(msg.alert);
			}
			//刷新用户列表
			if(msg.names){
				$('#userList').empty();
				$(msg.names).each(function(){
					$('#userList').append('<input type="checkbox" value='+this+'>'+this+'<br/>');
				});
			}
			//显示消息
			if(msg.username){
				$('#content').append(msg.content+"<br/>");
			}
		}
	}
	//向服务器发送数据
	function send(){
		//获取消息框对象
		var msg=$('#msg');
		//获取被选中的用户名对象
		var name=$('#userList :checked');
		//console.log(name.size());
		var obj=null;
		if(name.size()==0){
			//发广播
			obj={content:msg.val(),type:1};
		}else{
			//单聊
			obj={
					name:name.val(),//聊天目标的名字
					content:msg.val(),
					type:2//1广播，2单聊
			};
		}
		//将对象转换成JSON字符串
		var str=JSON.stringify(obj);
		ws.send(str);
		msg.val('');
	}
	
</script>
</head>
<body>
	<h3>欢迎 ${username } 使用本聊天室！</h3>
	<!-- 显示聊天内容 -->
	<div  id="content"  style="border: 1px solid black; width: 400px;
	 height: 300px;float: left; overflow:scroll;"></div>
	 
	 <!-- 显示用户列表 -->
	<div  id="userList" style="border: 1px solid black; width: 100px;
	 height: 300px;float:left; overflow:scroll;"></div>
		 
	<div style="clear: both;" >
		<input id="msg" /><button onclick="send();"  >send</button>
	</div>
</body>
</html>