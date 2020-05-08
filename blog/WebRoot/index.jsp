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
    
    <!-- <meta charset="utf-8"> -->
	<title>湖师小空间</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript" src="js/tool.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/base_drag.js"></script>
	<script type="text/javascript" src="js/base_form.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
  </head>
  
  <body>
  <%-- <%@ include file="index_header.jsp" %> --%>
  <jsp:include page="index_header.jsp"></jsp:include>
    
	<nav>
		<ul>
			<li class="active"><a href="index.jsp">首页</li></a>
			<li class="other"><a href="blog.jsp">博文列表</a></li>
			<li class="other"><a href="music.html">动感音乐</a></li>
			<li class="other"><a href="photo.html">精彩相册</a></li>
		</ul>
	</nav>
	
	<jsp:include page="banner.jsp"></jsp:include>
	<div id="main">
		<section>
			<div class="blog_sort sort_1">
				<div class="col">
					<a href="ShowArticleServlet?blog_id=${index_blogs[0].blog_id}&href=<%=request.getRequestURL()%>">
						<div class="row row_1">
							<h2>${index_blogs[0].title }</h2>
							<p>${index_blogs[0].describe }</p>
						</div>
					</a>
					<a href="ShowArticleServlet?blog_id=${index_blogs[1].blog_id}&href=<%=request.getRequestURL()%>">
						<div class="row row_2">
							<h2>${index_blogs[1].title }</h2>
							<p>${index_blogs[1].describe }</p>
						</div>
					</a>
				</div>
				<div class="col col_2">
					<a href="ShowArticleServlet?blog_id=${index_blogs[2].blog_id}&href=<%=request.getRequestURL()%>">
						<div class="row row_1">
							<h2>${index_blogs[2].title }</h2>
							<p>${index_blogs[2].describe }</p>
						</div>
					</a>
					<a href="ShowArticleServlet?blog_id=${index_blogs[3].blog_id}&href=<%=request.getRequestURL()%>">
						<div class="row row_2">
							<h2>${index_blogs[3].title }</h2>
							<p>${index_blogs[3].describe }</p>
						</div>
					</a>
				</div>
				<div class="col">
					<a href="ShowArticleServlet?blog_id=${index_blogs[4].blog_id}&href=<%=request.getRequestURL()%>">
						<div class="row row_1">
							<h2>${index_blogs[4].title }</h2>
							<p>${index_blogs[4].describe }</p>
						</div>
					</a>
					<a href="ShowArticleServlet?blog_id=${index_blogs[5].blog_id}&href=<%=request.getRequestURL()%>">
						<div class="row row_2">
							<h2>${index_blogs[5].title }</h2>
							<p>${index_blogs[5].describe }</p>
						</div>
					</a>
				</div>
			</div>
		</section>
		<aside>
			<h2 class="h2">公告栏<a class="more" href="#">更多>></a></h2>
			<hr style="color:#ccc;">
			<ul calss="notices">
				<c:forEach items="${allNotice}" var="notice"  end="5">
					<a href="ShowArticleServlet?notice_id=${notice.notice_id }&href=<%=request.getRequestURL()%>"><li>${notice.title }</li></a>
				</c:forEach>
			</ul>
		</aside>
	</div>
	<div class="clearfix"></div> 
	<footer>
		<div class="top">
			<p>中国·浙江 湖州市二环东路759号（313000） 浙ICP备10025412号 浙公网安备 33050202000195号</p>
		</div>
	</footer>
  </body>
</html>
