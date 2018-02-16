<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
<link rel="stylesheet" href="styles/regist.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/regist.js"></script>

</head>
<body>
	<h2 id="error">${msg}</h2>
	<c:url var="url" value="regist.action" />
	<form action="${url}" method="post">
	<p>
		账号&nbsp;&nbsp;<input  id="count" name="username" value="${param.username }"/>
		<span id="count-msg" class="error"></span>
	</p>
	<p>
		昵称&nbsp;&nbsp;<input  id="nick" name="nick" value="${param.nick }"/>
		<span id="nick-msg" class="error"></span>
	</p>
	<p>
		密码&nbsp;&nbsp;<input type="password"  id="password" name="password" value="${param.password }"/>
		<span id="pwd-msg" class="error"></span>
	</p>
	<p>
		确认密码<input type="password"  id="confirm" name="confirm" value="${param.confirm }"/>
		<span id="confirm-msg" class="error"></span>
	</p>
	<button type="submit" id="regist">注册</button>
	</form>
</body>
</html>