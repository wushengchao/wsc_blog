<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="utf-8">
	<title>管理中心</title>
	<link rel="stylesheet" type="text/css" href="admin/css/index.css">
  </head>
  	
  <body>
    <header>
		<ul>
			<li>欢迎您：${admin.admin_name}!</li>
			<li><a href="admin_login.html">退出</a></li>
		</ul>
		<h1>湖师空间系统管理中心<sup>V2017</sup></h1>
		
	</header>
	<nav>
		<ul>
			<a href="admin/blog_admin.jsp" target="iframe"><li>博文管理</li></a>
			<a href="admin/user_admin.jsp" target="iframe"><li>用户管理</li></a>
			<a href="admin/notice_admin.jsp" target="iframe"><li>公告管理</li></a>
			<a href="admin/index_admin.jsp" target="iframe"><li>首页管理</li></a>
			
		</ul>
	</nav>
		
	<section>
		<iframe  id="iframe" name="iframe" frameBorder=0 scrolling=no>
    	
    	</iframe>
	</section>
  </body>
</html>
