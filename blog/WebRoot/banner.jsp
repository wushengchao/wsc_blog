<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript" src="js/tool.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/base_drag.js"></script>
	<script type="text/javascript" src="js/base_form.js"></script>
    <script type="text/javascript" src="js/banner.js"></script>
    <link rel="stylesheet" type="text/css" href="css/banner.css">
  </head>
  
  <body>
    <div id="banner">
		<div class="in_banner">
			<div class="banner_img">
				<img src="images/banner1.jpg" bg_color="#8A98C5" width="1263" height="400">
				<img src="images/banner2.jpg" bg_color="#F15E64" width="1263" height="400">
				<img src="images/banner3.jpg" bg_color="#B2D1D3" width="1263" height="400">
				<ul>
					<li>●</li>
					<li>●</li>
					<li>●</li>
				</ul>
				<span></span>
			</div>
			<div class="type">
				<h2 class="h2">博文类别</h2>
					<ul>
						<li style="display: none"></li>
						<li>
							<a href="#">时事类</a>
							<div class="blog_sort sort_1">
								<div class="col">
									<div class="row row_1">
										<h2>女教师“矜持牌坊” 副校长“色心难控” </h2>
										<p>有关校长系列的丑闻，近几年来可谓是持续不断。祸害青苗，猥亵同僚，潜规女教师，貌似已经成了“校长们”的家常便饭。</p>
									</div>
									<div class="row row_2">
										<h2>贫穷与富有只是一场病的距离</h2>
										<p>5月18日，网易河北频道28岁的女编辑王雅珊因病去世，这是一个月内第五名因病去世的媒体人；3月7日，王雅珊在微博写下“生命进入倒计...</p>
									</div>
								</div>
								<div class="col col_2">
									<div class="row row_1">
										<h2>高铁：不要犯“越地卖履”式错误</h2>
										<p>日前有消息称，中国在拉美承建的第一条高铁——全长462.27公里、设计时速220公里/..</p>
									</div>
									<div class="row row_2">
										<h2>马晓年性学工作站落户成都</h2>
										<p>马晓年性学工作站落户成都九龙医院 强强联手打造中国最强性治疗基地造福蓉城及全国百姓 2016年5月9日，“马晓年性学工...</p>
									</div>
								</div>
								<div class="col">
									<div class="row row_1">
										<h2>江苏人对高考减招的愤怒</h2>
										<p>贾也：从江苏到温哥华——中国教育像往公厕里扔孩子，总能激起公愤。</p>
									</div>
									<div class="row row_2">
										<h2>小偷的“工作计划”为何如此有趣？</h2>
										<p>一个小偷的“工作计划”为何如此有趣？ </p>
									</div>
								</div>
							</div>
						</li>
						<li>
							<a href="#">娱乐类</a>
							<div class="blog_sort sort_2">
								<div class="col">
									<div class="row row_1">
										<h2>唐僧在是否动了真情？ </h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？唐僧在女儿国，主要是表现权力和美色对唐僧的诱惑。唐僧在女儿国，主要是表现权力和美色对唐僧的诱惑</p>
									</div>
									<div class="row row_2">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
								</div>
								<div class="col col_2">
									<div class="row row_1">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
									<div class="row row_2">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
								</div>
								<div class="col">
									<div class="row row_1">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
									<div class="row row_2">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
								</div>
							</div>
						</li>
						<li><a href="#">生活类</a>
							<div class="blog_sort sort_3">
								<div class="col">
									<div class="row row_1">
										<h2>唐僧在女儿国是否动了真情？ </h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？唐僧在女儿国，主要是表现权力和美色对唐僧的诱惑。唐僧在女儿国，主要是表现权力和美色对唐僧的诱惑</p>
									</div>
									<div class="row row_2">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
								</div>
								<div class="col col_2">
									<div class="row row_1">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
									<div class="row row_2">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
								</div>
								<div class="col">
									<div class="row row_1">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
									<div class="row row_2">
										<h2>唐僧爱谁</h2>
										<p>孙悟空？猪八戒？沙和尚？白龙马？白骨精？</p>
									</div>
								</div>
							</div>
						</li>
						<li><a href="#">体育类</a></li>
						<li><a href="#">学术类</a></li>
						<li><a href="#">其　他</a></li>
					</ul>
					<div style="background: #1D59A8;height:30px;width:300px"></div>	

			</div>
		</div>
	</div>
  </body>
</html>
