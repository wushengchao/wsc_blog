<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="utf-8">
	<title>博文列表</title>
	<link rel="stylesheet" type="text/css" href="css/blog.css">
	<script type="text/javascript" src="js/tool.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/base_drag.js"></script>
	<script type="text/javascript" src="js/blog.js"></script>
	<style type="text/css">
		.sort_<%=request.getParameter("sort_id")%>{
			background: #eee;
			color:#B80000;
			width:264px;
			font-weight: bold;
			border:none;
		}
	
	</style>
  </head>
  
  <body>
 	 <%@ include file="index_header.jsp" %>
     <%-- <jsp:include page="index_header.jsp"></jsp:include> --%>
	<nav>
		<ul>
			<li class="other"><a href="index.jsp">首页</li></a>
			<li class="active"><a href="blog.jsp">博文列表</a></li>
			<li class="other"><a href="music.html">动感音乐</a></li>
			<li class="other"><a href="photo.html">精彩相册</a></li>
		</ul>
	</nav>
	<div id="main">
		
		<aside>
			<ul class="sort">
				<a href="BlogSortListByPage?sort_id=9&page=1"><li class="sort_9 sort_null">全　部</li></a>
				<a href="BlogSortListByPage?sort_id=0&page=1"><li class="sort_0">校园类</li></a>
				<a href="BlogSortListByPage?sort_id=1&page=1"><li class="sort_1">时事类</li></a>
				<a href="BlogSortListByPage?sort_id=2&page=1"><li class="sort_2">娱乐类</li></a>
				<a href="BlogSortListByPage?sort_id=3&page=1"><li class="sort_3">生活类</li></a>
				<a href="BlogSortListByPage?sort_id=4&page=1"><li class="sort_4">体育类</li></a>	
				<a href="BlogSortListByPage?sort_id=5&page=1"><li class="sort_5">学术类</li></a>	
				<a href="BlogSortListByPage?sort_id=6&page=1"><li class="sort_6">其他类</li></a>	
			</ul>
		</aside>
		<section>
			<c:forEach items="${blogList}" var="blog">
			<a href="ShowArticleServlet?blog_id=${blog.blog_id}&href=<%=request.getRequestURL()%>">
				<div class="content">
					<h2>${blog.title}<em>${blog.time}</em></h2>
					<div class="p">
						<div class="blog_content">${blog.content}</div>
					</div>
				</div>
			</a>
			</c:forEach>
	
			<div class="page">
				<ul>
					<a href="BlogSortListByPage?page=${page.firstPage}&sort_id=${sort_id}"><li>首页</li></a>
					<a href="BlogSortListByPage?page=${page.prePage}&sort_id=${sort_id}"><li>上一页</li></a>
					<li class="nowPage">${page.currentPage}/${page.totalPage}</li>
					<a href="BlogSortListByPage?page=${page.nextPage}&sort_id=${sort_id}"><li>下一页</li></a>
					<a href="BlogSortListByPage?page=${page.lastPage}&sort_id=${sort_id}"><li>尾页</li></a>
				</ul>
			</div>		
		</section>
	</div>
	<div class="clearfix"></div> 
	<footer>
		<div class="top">
			<p>中国·浙江 湖州市二环东路759号（313000） 浙ICP备10025412号 浙公网安备 33050202000195号</p>
		</div>
	</footer>
  </body>
</html>
