<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		#note{
			width: 1063px;
			margin: 0 auto;
		}
		.write-note{
			height:200px;
			width:500px;
			border:1px solid #aaa;
		}
		.note-text{
			height: 162px;
			width:498px;
			resize: none;
			background: #eee;
			border:none;
		}
		.note-sub{
			border:1px solid #8A98C5;
			background: #8A98C5;
			height: 36px;
			width:80px;
			float: right;
			position: relative;
			top:-4px;
			color:#fff;
			letter-spacing: 3px;
			font-size: 20px;
		}
		.user-name{
			color:#666;
			letter-spacing: 1px;
			font-size: 18px;
		}
		.note-content p{
			color:#444;
			margin-left: 100px;
			font-size: 20px;
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
		<p class="author" style="text-align:right;letter-spacing:2px;color:#666;">(发布者: wsc)</p>
		<hr style="width:1063px;border-top:1px dashed #0066CC;">
		<br>
		<br>
		<div id="note" >
			<div class="write-note">
				<form action="AddNoteServlet">
					<textarea class="note-text" name="note-content"></textarea>
					<input type="hidden" value="${blog.blog_id }" name="blog_id"> 
					<input type="hidden" value="<%=request.getParameter("href") %>" name="href"> 
					<div class="note-bottom">
						<input type="submit" name="sub" class="note-sub" value="评论">
					</div>
					
				</form>
			</div>
		</div>
		<br><br><br>
		<h1 style="font-size: 24px;color:#444;letter-spacing: 2px;">最新评论</h1>
		<c:forEach items="${notes }" var="note">
			<br><br>
			<div>
				<p class="user-name">用户id：${note.user_name }:</p><br>
				<div class="note-content">
					<p>${note.note_content }</p>
				</div>
				<p class="time">发布时间: (<span class="time1">${note.note_time }</span>)</p>
				<hr style="border-top:1px dashed #999;">
			</div>
		</c:forEach>
		
	</div>
	　　
	
	
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
