<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="utf-8">
	<title>个人中心</title>
	<link rel="stylesheet" type="text/css" href="user/css/bloglist.css">
	<script type="text/javascript" src="user/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="user/js/jquery.cookie.js"></script>
	
  </head>
  
  <body>
    <header>
		<img class="icon" src="user/images/icon1.jpg">
		<a href="index.jsp"><span class="return"><<返回首页</span></a>
		<h2><span class="username">还会恢复</span>　 的个人中心</h2>
		
	</header>
	<nav>
		<ul>
			<li class="active"><a href="user/myBlogList.jsp">博文列表</a></li>
			<li class="other"><a href="user/myInfo.jsp">个人信息</a></li>
			<li class="other"><a href="#">音乐收藏</a></li>
			<li class="other"><a href="#">我的相册</a></li>
			
		</ul>
	</nav>
	<div id="main">
		<section>
			<a href="blogEditor.html"><div class="write">写博客</div></a>
			<div class="blog_list">
				<ul class="titles">
					<c:forEach items="${blogList}" var="blog">
						<li>
							<em>${blog.time}</em>

							<a href="DeleteBlogServlet?blog_id=${blog.blog_id}&href=<%=request.getRequestURL() %>"  onclick="if(confirm('确定删除?')==false)return false;"><span class="delete">删除</span></a>
							<a href="ShowArticleServlet?blog_id=${blog.blog_id}&href=<%=request.getRequestURL() %>"><span class="show">查看</span></a>
							<p class="title">${blog.title }</p>
						</li>
					</a>
					</c:forEach>
				</ul>	
				<div class="page">
					<ul>
						<a href="MyBlogPageServlet?page=${sessionScope.page.firstPage}"><li>首页</li></a>
						<a href="MyBlogPageServlet?page=${sessionScope.page.prePage}"><li>上一页</li></a>
						<li class="nowPage">${sessionScope.page.currentPage}/${sessionScope.page.totalPage}</li>
						<a href="MyBlogPageServlet?page=${sessionScope.page.nextPage}"><li>下一页</li></a>
						<a href="MyBlogPageServlet?page=${sessionScope.page.lastPage}"><li>尾页</li></a>
					</ul>
				</div>	
			</div>
		</section>
		<aside>
			<div class="quantity">
				<ul>
					<li>
						<p>209</p>
						<span>博文</span>
					</li>
					<li>
						<p>419</p>
						<span>照片</span>
					</li>
					<li>
						<p>199091</p>
						<span>访问量</span>
					</li>
				</ul>
			</div>	

		</aside>
	</div>



	<div class="clearfix"></div> 
	<footer>
		<div class="top">
			<p>中国·浙江 湖州市二环东路759号（313000） 浙ICP备10025412号 浙公网安备 33050202000195号</p>
		</div>
	</footer>
	<script type="text/javascript">
		$(function(){
			$(".blog_list li").hover(function(){
				$(this).find(".delete,.show").show();
			},function(){
				$(".delete,.show").hide();
			});
			$(".username").html($.cookie("user"));
			for(var i=0;i<$(".titles em").length;i++){
				var time=$(".titles em").eq(i).html();
				//alert(time);
				var end=time.lastIndexOf(" ");
				time=time.substring(0,end);
				//alert(time);
				$(".titles em").eq(i).html(time);
			}
			
			
		});
	</script>
  </body>
</html>
