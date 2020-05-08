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
    
    <title>博客管理</title>
	<link rel="stylesheet" type="text/css" href="admin/css/user_admin.css">
	<script type="text/javascript" src="admin/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src=""></script>

  </head>
  
  <body>
    <aside>
		<ul>
			<li class="quantity">用户量</li>
			<li class="users">用户列表</li>
		</ul>
	</aside>
	<section>
		<div class="list user-quantity">
			<h2>当前用户总量：<em>${user_size }</em> 人</h2>
			<h2>今日新增用户：<em>${new_size }</em>    人</h2>
			<div class="new-user">
				<table cellpadding="5" cellspacing="0"  >
				<h2>新增用户表：</h2>
				<tr>
					<th width="100">用户ID</th>
					<th width="100">用户名</th>
					<th width="100">密码</th>
				</tr>
				<c:forEach items="${new_users }" var="user">
					<tr>
						<td >${user.userId }</td>
						<td >${user.userName }</td>
						<td >${user.password }</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			
		</div>
		<div class="list user-list" style="displsy:none">
			<table cellpadding="5" cellspacing="0" >
				<tr>
					<th width="100">用户ID</th>
					<th width="100">用户名</th>
					<th width="100">密码</th>
				</tr>
				<c:forEach items="${users }" var="user">
					<tr>
						<td >${user.userId }</td>
						<td >${user.userName }</td>
						<td >${user.password }</td>
					</tr>
				</c:forEach>
	
			</table>	
		</div>
	</section>
	<script type="text/javascript">
		$(function(){
			$("aside .quantity").click(function(){
				$("aside .quantity").css('color','red');
				$("aside .users").css('color','#2F6982');
				$("section .user-quantity").show('slow');
				$("section .user-list").hide('slow');
			});
			$("aside .users").click(function(){
				$("aside .users").css('color','red');
				$("aside .quantity").css('color','#2F6982');
				$("section .user-list").show('slow');
				$("section .user-quantity").hide('slow');
			});
			if(${new_size }==0){
				$('.new-user').hide();
			}
		});	
	</script>
  </body>
</html>
