<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="utf-8">
	<title>审核</title>
	<link rel="stylesheet" type="text/css" href="admin/css/blog_review.css">
	<script type="text/javascript" src="admin/js/jquery-3.2.1.js"></script>

  </head>
  
  <body>
    <div class="return"><a href="javascript:;" onclick="window.history.go(-1);"><<返 回</a></div>
	<section>
		<form action="AddIndexBlogServlet">
			<table >
				<tr>
					<td><span class="type">标题</span></td>
					<td><h2 class="title">${blog.title }</h2></td>
				</tr>
				<tr>
					<td style="vertical-align:top; padding-top:8px;"><span class="type">内容</span></td>
					<td>
						<div class="content-out" style="height:230px;">
							<div class="content">
								${blog.content }
							</div>
						</div>
						
					</td>
				</tr>
				<tr style="height:40px; color:#fff; ">
					<td style="vertical-align:top; padding-top:18px;"><span class="type">描述</span></td>
					<td style="padding-top:10px;">
						<div class="content-out" style="height:50px;padding-top:5; ">
							<textarea class="content" name="describe" style="resize:none; width:850px;border:none;" >
								
							</textarea>
						</div>
						
						
					</td>
				</tr>
				<tr><input type="hidden" name="blog_id" value="${blog.blog_id }"></tr>
				<tr><input type="hidden" name="title" value="${blog.title }"></tr>
				<tr >
					<td></td>
					<td class="result" style="padding-top:10px;">
						<input type="submit" class="button succ" style="" value="推送至首页">
					</td>
				</tr>
			</table>
		</form>

	</section>
	<script type="text/javascript">
		$(function(){
			
			$(".button").click(function(){
				alert("请求已提交!");
			});
		});
	</script>
  </body>
</html>
