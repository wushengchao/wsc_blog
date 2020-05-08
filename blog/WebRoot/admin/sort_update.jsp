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
		<form action="BlogReviewServlet" method="post">
			<table >
				<tr>
					<td><span class="type">审核状态</span></td>
					<td><h2 style="color:#fff;letter-spacing:1px;">${blog.status }</h2>	</td>
					
				</tr>
				<tr >
					<td style="padding-top:10px;"><span class="type">当前类别</span></td>
					<td style="padding-top:10px;"><h2 style="color:#fff;letter-spacing:1px;">未知</h2></td>
				</tr>
				<tr>
					<td><span class="type">标题</span></td>
					<td><h2 class="title">${blog.title }</h2></td>
				</tr>
				<tr>
					<td style="vertical-align:top; padding-top:8px;"><span class="type">内容</span></td>
					<td>
						<div class="content-out" style="height:220px;">
							<div class="content">
								${blog.content }
							</div>
						</div>
						
					</td>
				</tr>
				<tr style="height:40px; color:#fff;">
					<td><span class="type">分类</span></td>
					<td>
						<label>校园类<input type="radio" name="sort" value="0"></label>&nbsp;&nbsp;
						<label>时事类<input type="radio" name="sort" value="1"></label>&nbsp;&nbsp;
						<label>娱乐类<input type="radio" name="sort" value="2"></label>&nbsp;&nbsp;
						<label>生活类<input type="radio" name="sort" value="3"></label>&nbsp;&nbsp;
						<label>体育类<input type="radio" name="sort" value="4"></label>&nbsp;&nbsp;
						<label>学术类<input type="radio" name="sort" value="5"></label>&nbsp;&nbsp;
						<label>其他类<input type="radio" name="sort" value="6"></label>&nbsp;&nbsp;
					</td>
				</tr>
				<tr><input type="hidden" name="blog_id" value="${blog.blog_id }"><input type="hidden" name="href" value="<%=request.getRequestURL() %>"></tr>
				<tr>
					<td></td>
					<td class="result">
						<input type="submit" class="button succ" value="更改分类">
						<a href="BlogReviewServlet?blog_id=${blog.blog_id}&href=<%=request.getRequestURL() %>" onclick="if(confirm('确定该博文违规?')==false)return false;">
							<input type="button" class="button fail" value="不通过">
						</a>
					</td>
				</tr>
			</table>
		</form>

	</section>
  </body>
</html>
