//登录页面中的脚本

//页面加载完执行该函数
$(function() {
	// console.log('ok');
	
	// focus()获取焦点
	$('#count').focus();
	$('#regist').click(toRegistPage);
});
function toRegistPage(){
	location.href='regist.jsp';
}

