<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<h2 id="error">${error}</h2>
	<h2 id="success">${success }</h2>
	<c:url var="url" value="login.action" />
	<form action="${url}" method="post">
	<p>
		账号：<input  id="count" name="username" value="${param.username }"/>
		<span id="count-msg" class="error"></span>
	</p>
	<p>
		密码：<input type="password"  id="password" name="password" value="${param.password }"/>
		<span id="pwd-msg" class="error"></span>
	</p>
	<button type="submit" id="login">登录</button>
	<input type="button" value="注册" id="regist"/>
	</form>
	
</body>
</html>