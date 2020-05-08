<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<title>审核</title>
	<link rel="stylesheet" type="text/css" href="admin/css/blog_review.css">

  </head>
  
  <body>
    <div class="return"><a href="javascript:;" onclick="window.history.go(-1);"><<返 回</a></div>
	<section>
			<table >
				<tr>
					<td><span class="type">审核状态</span></td>
					<td><h2 style="color:#fff;letter-spacing:1px;">${blog.status }</h2></td>
				</tr>
				<tr>
					<td><span class="type">标题</span></td>
					<td><h2 class="title">${blog.title }</h2></td>
				</tr>
				<tr>
					<td style="vertical-align:top; padding-top:8px;"><span class="type">内容</span></td>
					<td>
						<div class="content-out">
							<div class="content">
								${blog.content }
							</div>
						</div>
						
					</td>
				</tr>
			</table>

	</section>
  </body>
</html>
