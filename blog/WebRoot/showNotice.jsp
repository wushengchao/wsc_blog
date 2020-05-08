<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${requestScope.blog.title} </title>
    <script type="text/javascript" src="js/tool.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
    <link rel="stylesheet" type="text/css" href="css/basic.css">
	<link rel="stylesheet" type="text/css" href="css/blogEditor.css">
    <style type="text/css">	
		#article h2{
			font-size:30px;
			text-align:left;
			font-weight: normal;
			color: #333;
			letter-spacing: 2px;
		}
		hr{
			color:#ccc;
		}
		.time{
			text-align: right;
			color:#666;
		}
		.content{
			margin-top:30px;
		}
		.return{
			width:1063px;
			margin:0 auto;
			text-align:right;
			margin-top:230px;
			letter-spacing:2px;
			font-size:20px;
		}
		.return a{
			color:#666;
			border:1px solid #999;
			padding:5px 20px;
			border-radius:4px;
		}
		.return a:hover{
			color:#fff;
			background:#999;
		}
		
	</style>
  </head>
  
  <body>
  	<div class="return"><a href=<%=request.getParameter("href") %>>返 回</a></div>
    <div style="width:1063px;margin:30px auto" id="article">
		<h2>${requestScope.blog.title}</h2>
		<br>
		<p class="time"></p>
		<p class="time">发布时间：(<span class="time1">${requestScope.blog.time }</span>)</p>
		<hr>
		<div class="content">
			${requestScope.blog.content}
		</div>
		<br>
		<br>　
	</div>
	　　
	
	<br>
	<br>
	
	<br>
	<br>
	<script type="text/javascript">
		var time=$("#article .time1").html();
		//alert(time);
		var end=time.lastIndexOf(".");
		time=time.substring(0,end);
		//alert(time);
		$("#article .time1").html(time);
	</script>
  </body>
</html>
