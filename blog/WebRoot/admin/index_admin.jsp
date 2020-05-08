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
    
    <title>首页管理</title>
	<link rel="stylesheet" type="text/css" href="admin/css/index_admin.css">
	<script type="text/javascript" src="admin/js/jquery-3.2.1.js"></script>
	<style type="text/css">
		
		section ${show_list}{
			display:block;
		}
		aside ${red_li}{
			color:red;
		}
		
	</style>
  </head>
  
  <body>
   	<aside>
		<ul>
			<li class="index-list">首页列表</li>
			<li class="blog-choose">首页设置</li>
		</ul>
	</aside>
	<section>
		<div class="list index-blog-list">
			<ul class="col col1">
				<c:forEach items="${index_blogs}" var="blog" end="5">
					<a href=""><li>${blog.title}</li></a>
				</c:forEach>
	
		
			
			</ul>
			
		</div>
		<div class="list blog-choose-list">
			<ul class="col col1">
				<c:forEach items="${pass_blogList}" var="blog" end="7">
					<a href="ShowArticleServlet?blog_id=${blog.blog_id}&index='admin'"><li><span class="title">${blog.title}</span></li></a>
				</c:forEach>
			</ul>
			<ul class="col col2">
				<c:forEach items="${pass_blogList}" var="blog" begin="8">
					<a href="ShowArticleServlet?blog_id=${blog.blog_id}&index='admin'"><li><span class="title">${blog.title}</span></li></a>
				</c:forEach>
			</ul>
			<div class="clearfix"></div>
			<div class="page">
				<ul>
					<a href="AdminBlogPageServlet?pass_page=${pass_page.prePage}"><li>首页</li></a>
					<a href="AdminBlogPageServlet?pass_page=${pass_page.prePage}"><li>上一页</li></a>
					<li class="nowPage">${pass_page.currentPage} /${pass_page.totalPage }</li>
					<a href="AdminBlogPageServlet?pass_page=${pass_page.nextPage}"><li>下一页</li></a>
					<a href="AdminBlogPageServlet?pass_page=${pass_page.lastPage}"><li>尾页</li></a>
				</ul>
			</div>
		</div>

	</section>
	<script type="text/javascript">
		$(function(){
			$("aside .index-list").click(function(){
				$("aside .index-list").css('color','red');
				$("aside .blog-choose").css('color','#2F6982');
				$("section .index-blog-list").show('slow');
				$("section .blog-choose-list").hide('slow');
			});
			$("aside .blog-choose").click(function(){
				$("aside .blog-choose").css('color','red');
				$("aside .index-list").css('color','#2F6982');
				$("section .blog-choose-list").show('slow');
				$("section .index-blog-list").hide('slow');
			});
		});	
	</script>
  </body>
</html>
