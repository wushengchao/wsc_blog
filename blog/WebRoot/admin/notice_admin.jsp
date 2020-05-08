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
    
    <title>博客管理</title>
	<link rel="stylesheet" type="text/css" href="admin/css/notice_admin.css">
	<link rel="stylesheet" type="text/css" href="editor/css/wangEditor-1.3.13.2.css">
	<script type="text/javascript" src="js/blog_admin.js"></script>
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
			<li class="all-notice">全部公告(${notice_size})</li>
			<li class="write-notice">发布新公告</li>
		</ul>
	</aside>
	<section>
		<div class="list notice-list" >
			<ul class="col col1">
				<c:forEach items="${admin_notice_list}" var="notice" end="7">
					<a href="#"><li><span class="title">${notice.title}</span></li></a>
				</c:forEach>
			</ul>
			<ul class="col col2">
				<c:forEach items="${admin_notice_list}" var="notice" begin="8">
					<a href="#"><li><span class="title">${notice.title}</span></li></a>
				</c:forEach>
			</ul>
			<div class="clearfix"></div>
			<div class="page">
				<ul>
					<a href="AdminBlogPageServlet?notice_page=${notice_page.firstPage}"><li>首页</li></a>
					<a href="AdminBlogPageServlet?notice_page=${notice_page.prePage}"><li>上一页</li></a>
					<li class="nowPage">${notice_page.currentPage} /${notice_page.totalPage }</li>
					<a href="AdminBlogPageServlet?notice_page=${notice_page.nextPage}"><li>下一页</li></a>
					<a href="AdminBlogPageServlet?notice_page=${notice_page.lastPage}"><li>尾页</li></a>
				</ul>
			</div>
		</div>
		<div style="width:1000px;margin: 0 50px;" class="editor">
			<form action="AddNoticeServlet">
				<input type="text" id="title" name="title" style="" placeholder="//请输入标题...">
				<textarea id='notice_content' name='content'>
				</textarea>
				<input type="submit" id="sub" class="button btn" name="submit" value="发布公告">
				<input type="button" id="clear" class="btn" name="submit" value="清空内容">
			</form>
		</div>
	</section>
	<script type="text/javascript" src='editor/js/jquery-1.10.2.min.js'></script>
	<script type="text/javascript" src='editor/js/wangEditor-1.3.13.2.js'></script>
	<script type="text/javascript">
		$(function(){
			var editor = $('#notice_content').wangEditor();
			$('#clear').click(function(e){
				editor.html('');
			});
			$('.wangEditor-textarea-container').css('background','#fff');
			$('.wangEditor-textarea').css('background','#fff').css('color','#666');
			
			//侧栏点击
			$("aside .all-notice").click(function(){
				$("aside .all-notice").css('color','red');
				$("aside .write-notice").css('color','#2F6982');
				$("section .notice-list").show('slow');
				$("section .editor").hide('slow');
			});
			$("aside .write-notice").click(function(){
				$("aside .all-notice").css('color','#2F6982');
				$("aside .write-notice").css('color','red');
				$("section .editor").show('slow');
				$("section .notice-list").hide('slow');
			});
			
			$(".button").click(function(){
				alert("公告内容已提交!");
			});
		});	
		
	</script>
  </body>
</html>
