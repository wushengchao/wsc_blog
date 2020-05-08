<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<head>
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/tool.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/base_drag.js"></script>
	<script type="text/javascript" src="js/base_form.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/basic.js"></script>
</head>
<header>
	<div class="logo">
		<img src="images/logo.png" height="80" width="240">
	</div>  
	<ul class="nologin">
		<li class="reg">注册</li> &nbsp;|&nbsp;
		<li class="login">登录</li>
	</ul>
	<ul class="userinfo">
		<li class="info1">您好</li> &nbsp;|&nbsp;
		<a href="user/myBlogList.jsp"><li class="user">用户中心</li></a> &nbsp;|&nbsp;
		<a ><li class="exit">退出</li></a>
	</ul>
</header>
<div id="screen"></div>
<div id="loading">
	<p>加载中</p>
</div>
<div id="success">
	<p>成功</p>
</div>
<div id="login">
	<h2>网站登录<img class="close" src="images/close.png"></h2>
	<form name="login">
		<div class="warn"></div>
		<div class="user">
			<label for="user">账号：</label>
			<input class="text" type="text" name="user" id="user">
		</div>
		<div class="pass">
			<label for="password">密码：</label>
			<input class="text" type="password" name="pass" id="pass">
		</div>
		<div class="submit" name="sub">
			<input type="button" value="">
		</div>
	</form>
	<div class="other"><a href="#">注册新用户</a> | <a href="#">忘记密码?</a></div>
</div>
<div id="reg">
	<h2>用户注册<img class="close" src="images/close.png"></h2>
	<form name="reg">
		<dl>
			<dd> 用 户 名 ： <input type="text" name="user" class="text" autocomplete="off" style="margin-left:2px;">
				<span class="info info_user">请输入用户名,2~20位，由字母，数字和下划线组成!</span>
				<span class="error error_user">输入不合法，请重新输入！</span>
				<span class="succ succ_user">可用！</span>
				<span class="loading">正在检测用户名...</span>
			</dd>
			<dd>密　　码： <input type="password" name="pass" class="text">
				<span class="info info_pass">
					<p>安全级别：<strong class="s s1">■■</strong><strong class="s s2">■■</strong><strong class="s s3">■■ </strong><strong class="s s4" style="font-weight:normal;"></strong></p>
					<p><strong class="q q1">○</strong> 6-20个字符</p>
					<p><strong class="q q2">○</strong> 只能包含大小写字母、数字和非空字符</p>
					<p><strong class="q q3">○</strong> 大、小写字母、数字、非空字符，2种以上</p>
				</span>
				<span class="error error_pass">输入不合法，请重新输入！</span>
				<span class="succ succ_pass">可用！</span>
			</dd>
			<dd>密码确认： <input type="password" name="notpass" class="text">
				<span class="info info_notpass">请再一次输入密码！</span>
				<span class="error error_notpass">输入不一致，请重新输入！</span>
				<span class="succ succ_notpass">可用！</span>
			</dd>
			
			
			<dd style="padding:10px 0 0 72px"><input type="button" name="sub" class="submit"></dd>
		</dl>
	</form>
</div>

