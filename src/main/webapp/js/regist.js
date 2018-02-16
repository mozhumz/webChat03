//注册页面中的脚本

//页面加载完执行该函数
$(function() {
//	 console.log('ok');
	// focus()获取焦点
	$('#count').focus().blur(checkUsername);
	$('#password').blur(checkPwd);
});

//注册前，检查用户名格式
function checkUsername(){
	// console.log('checkUsername');
	var name = $('#count').val();
	if (!name) {
		$('#count-msg').empty().html('不能为空');
		return false;
	}
	var reg = /^\w{3,16}$/;
	if (reg.test(name)) {
		$('#count-msg').empty();
		return true;
	} else {
		$('#count-msg').empty().html('3-16字符');
		return false;
	}
}

//注册前，检查密码格式
function checkPwd(){
	// console.log('checkPwd');
	var pwd = $('#password').val();
	if (!pwd) {
		$('#pwd-msg').empty().html('不能为空');
		return false;
	}
	var reg = /^\w{3,16}$/;
	if (reg.test(pwd)) {
		$('#pwd-msg').empty();
		return true;
	} else {
		$('#pwd-msg').empty().html('3-16字符');
		return false;
	}
}

