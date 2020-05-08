<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>个人中心</title>
	<link rel="stylesheet" type="text/css" href="user/css/myInfo.css">
	<script type="text/javascript" src="user/js/tool.js"></script>
	<script type="text/javascript" src="user/js/base.js"></script>
	<script type="text/javascript" src="user/js/ajax.js"></script>
	<script type="text/javascript" src="user/js/base_drag.js"></script>
	<script type="text/javascript" src="user/js/base_form.js"></script>
	<script type="text/javascript" src="user/js/myInfo.js"></script>
  </head>
  
  <body>
	<div id="screen" style="display:none"></div>
	<div id="loading">
		<p>加载中</p>
	</div>
	<div id="success">
		<p>成功</p>
	</div>
	<header>
		<img class="icon" src="user/images/icon1.jpg">
		<a href="index.jsp"><span class="return"><<返回首页</span></a>
		<h2><span class="username">还会恢复</span>　 的个人中心</h2>
		
	</header>
	<nav>
		<ul>
			<li class="other"><a href="user/myBlogList.jsp">博文列表</a></li>
			<li class="active"><a href="#">个人信息</a></li>
			<li class="other"><a href="#">音乐收藏</a></li>
			<li class="other"><a href="#">我的相册</a></li>
			
		</ul>
	</nav>
	<div id="main">
		<section>
			<div class="write">编辑个人信息</div>
			<div class="user-info">
				<table>
					<tr>
						<td>姓　　名：</td>
						<td>${info.real_name }</td>
					</tr>
					<tr>
						<td>性　　别：</td>
						<td>${info.sex }</td>
					</tr>
					<tr>
						<td>学　　院：</td>
						<td>${info.institute }</td>
					</tr>
					<tr>
						<td>生　　日：</td>
						<td>${info.birthday }</td>
					</tr>
					<tr>
						<td>邮　　箱：</td>
						<td>${info.email }</td>
					</tr>
					<tr>
						<td>联系方式：</td>
						<td>${info.contact }</td>
					</tr>
					<tr>
						<td>个人简介：</td>
						<td>${info.introduce }</td>
					</tr>
				</table>
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
		<div id="reg">
			<h2>编辑个人信息<img class="close" src="images/close.png"></h2>
			<form name="reg">
				<dl>
					<input type="hidden" name="user_id" value="${user_id}">
					<dd>姓　　名 ： 
						<input type="text" name="real_name" class="text" autocomplete="off" value="${info.real_name }" >
					</dd>
					<dd>性　　别 ：
						<select name="sex">
							<option>男</option>
							<option>女</option>
						</select>
					</dd>
					<dd>学　　院 ： 
						<input type="text" name="institute" class="text" autocomplete="off" value="${info.institute }">
					</dd>
					<dd class="birthday">生　　日 ：
						<select name="year">
							<option value="0">&nbsp;年 </option>
						</select>&nbsp; -&nbsp;
						<select name="month">
							<option value="0">&nbsp;月 </option>
						</select>&nbsp; -&nbsp;
						<select name="day">
							<option value="0">&nbsp;日 </option>
						</select> 	
					</dd>
					<dd>电子邮件 ： <input type="text" name="email" class="text" autocomplete="off" value="${info.email }">
						<span class="succ succ_email">可用！</span>
						<ul class="all_email">
							<li><span></span>@qq.com</li>
							<li><span></span>@163.com</li>
							<li><span></span>@sohu.com</li>
							<li><span></span>@sina.com</li>
							<li><span></span>@gmail.com</li>
						</ul>
					</dd>
					<dd>联系方式 ： 
						<input type="text" name="contact" class="text" autocomplete="off" value="${info.contact }">
					</dd>
					<dd style="height:105px;"><span style="vertical-align:85px;">个人简介 ：</span> <textarea name="ps">${info.introduce }</textarea></dd>
					<dd class="ps">还可以输入<strong class="num">200</strong>字</dd>
					<dd style="display:none;" class="ps">已超过<strong class="num">35</strong>字，<span class="clear">清尾</span></dd>
					<dd style="padding:0 0 0 72px"><input type="button" name="sub" class="submit" value="提  交"></dd>
				</dl>
			</form>
		</div>
	</div>






	<div class="clearfix"></div> 
	<footer>
		<div class="top">
			<p>中国·浙江 湖州市二环东路759号（313000） 浙ICP备10025412号 浙公网安备 33050202000195号</p>
		</div>
		<!-- <div class="bottom">
			<p><a href="#">移动端</a>　｜　<a href="#">PC端</a>　｜　<a href="#">平板</a></p>
		</div> -->
	</footer>

	<script type="text/javascript">
	$(function(){
		$(".username").html(getCookie("user"));
	});
	</script>
  </body>
</html>
