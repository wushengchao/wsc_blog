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
    
    <meta charset="utf-8">
	<title>博客管理</title>
	<link rel="stylesheet" type="text/css" href="admin/css/basic.css">
	<script type="text/javascript" src="admin/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="admin/js/blog_admin.js"></script>
	<script type="text/javascript">
	
	</script>
	<style type="text/css">
		section .all-blog{
			display:none;
		}
		section .ready-blog{
			display: none;
		}
		section .wait-blog{
			display: none;
		}
		
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
			<li class="all">全　部 ( ${all_size} )</li>
			<li class="ready">已审核 ( ${ready_size} )</li>
			<li class="wait">待审核 ( ${wait_size} )</li>
		</ul>
	</aside>
	<section>
		<div class="list all-blog">
			<ul class="col col1">
				<c:forEach items="${blogList}" var="blog" end="7">
					<a href="ShowArticleServlet?blog_id=${blog.blog_id}&all='admin'"><li><span class="title">${blog.title}</span></li></a>
				</c:forEach>
			</ul>
			<ul class="col col2">
				<c:forEach items="${blogList}" var="blog" begin="8">
					<a href="ShowArticleServlet?blog_id=${blog.blog_id}&all='admin'"><li><span class="title">${blog.title}</span></li></a>
				</c:forEach>
			</ul>
			<div class="clearfix"></div>
			<div class="page">
				<ul>
					<a href="AdminBlogPageServlet?page=${all_page.firstPage}"><li>首页</li></a>
					<a href="AdminBlogPageServlet?page=${all_page.prePage}"><li>上一页</li></a>
					<li class="nowPage">${all_page.currentPage} /${all_page.totalPage }</li>
					<a href="AdminBlogPageServlet?page=${all_page.nextPage}"><li>下一页</li></a>
					<a href="AdminBlogPageServlet?page=${all_page.lastPage}"><li>尾页</li></a>
				</ul>
			</div>
		</div>
		<div class="list ready-blog">
			<ul class="col col1">
				<c:forEach items="${ready_blogList}" var="blog" end="7">
					<a href="ShowArticleServlet?blog_id=${blog.blog_id}&ready='admin'"><li><span class="title">${blog.title}</span></li></a>
				</c:forEach>
			</ul>
			<ul class="col col2">
				<c:forEach items="${ready_blogList}" var="blog" begin="8">
					<a href="ShowArticleServlet?blog_id=${blog.blog_id}&ready='admin'"><li><span class="title">${blog.title}</span></li></a>
				</c:forEach>
			</ul>
			<div class="clearfix"></div>
			<div class="page">
				<ul>
					<a href="AdminBlogPageServlet?ready_page=${ready_page.prePage}"><li>首页</li></a>
					<a href="AdminBlogPageServlet?ready_page=${ready_page.prePage}"><li>上一页</li></a>
					<li class="nowPage">${ready_page.currentPage} /${ready_page.totalPage }</li>
					<a href="AdminBlogPageServlet?ready_page=${ready_page.nextPage}"><li>下一页</li></a>
					<a href="AdminBlogPageServlet?ready_page=${ready_page.lastPage}"><li>尾页</li></a>
				</ul>
			</div>
		</div>
		<div class="list wait-blog">
			<ul class="col col1">
				<c:forEach items="${wait_blogList}" var="blog" end="7">
					<a href="ShowArticleServlet?blog_id=${blog.blog_id}&wait='admin'"><li><span class="title">${blog.title}</span></li></a>
				</c:forEach>
			</ul>
			<ul class="col col2">
				<c:forEach items="${wait_blogList}" var="blog" begin="8" end="15">
					<a href="ShowArticleServlet?blog_id=${blog.blog_id}&wait='admin'"><li><span class="title">${blog.title}</span></li></a>
				</c:forEach>
			</ul>
			<div class="clearfix"></div>
			<div class="page">
				<ul>
					<a href="AdminBlogPageServlet?wait_page=${wait_page.prePage}"><li>首页</li></a>
					<a href="AdminBlogPageServlet?wait_page=${wait_page.prePage}"><li>上一页</li></a>
					<li class="nowPage">${wait_page.currentPage} /${wait_page.totalPage }</li>
					<a href="AdminBlogPageServlet?wait_page=${wait_page.nextPage}"><li>下一页</li></a>
					<a href="AdminBlogPageServlet?wait_page=${wait_page.lastPage}"><li>尾页</li></a>
				</ul>
			</div>
		</div>
	</section>
  </body>
</html>
