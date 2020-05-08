<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/login.css">


  </head>
  
  <body>
    <div id="login" >
		<h2>网站登录<img class="close" src="images/close.png"></h2>
		<form name="login" action="LoginServlet" method="get">
			<div class="info"></div>
			<div class="user">
				<label for="user">账号：</label>
				<input class="text" type="text" id="user" name="user">
			</div>
			<div class="pass">
				<label for="password">密码：</label>
				<input class="text" type="password" id="password" name="pass">
			</div>
			<div class="submit" name="sub">
				<input type="submit" value="">
			</div>
		</form>
		<div class="other"><a href="#">注册新用户</a> | <a href="#">忘记密码?</a></div>
	</div>
  </body>
</html>
