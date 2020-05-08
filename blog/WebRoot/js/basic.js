$(function(){
	$('nav .other').hover(function() {
		$('nav .active a').css("background","#fff").css('color','#999');
	},function(){
		$('nav .active a').css("background","#1D59A8").css('color','#fff');
	});


/*登录框、注册框***/	
	var login=$('#login');		//登陆框
	var reg=$('#reg');			//注册框
	var blog=$('#blog'); 
	var screen=$('#screen');		//遮罩锁屏
	$('#header .login').click(function(){	//点击显示
		login.show().center(352,280);  //居中显示
		screen.lock().animate({
			attr:'o',
			target:70,
			t:50,
			step:10
		});		//同时遮罩显示,透明度渐变

	});
	$('#login .close').click(function(){	//点击close关闭
		login.hide();		//隐藏登陆框
		screen.animate({		//同时遮罩隐藏
			attr:'o',
			target:0,
			t:30,
			step:10,
			fn:function(){
				screen.unlock();	//透明动画结束后取消锁屏
			}
		});				
	});
	
	//注册框开关		
	$('#header .reg').click(function(){	//点击显示
		reg.show().center(450,300);  //居中显示
		$('form').first().reset();
		$('#reg .error').hide();
		$('#reg .succ').hide();
		screen.lock().animate({
			attr:'o',
			target:70,
			t:50,
			step:10
		});		//同时遮罩显示,透明度渐变

	});
	$('#reg .close').click(function(){	//点击close关闭
		reg.hide();		//隐藏
		screen.animate({		//同时遮罩隐藏
			attr:'o',
			target:0,
			t:30,
			step:10,
			fn:function(){
				screen.unlock();	//透明动画结束后取消锁屏
			}
		});				
	});
	//登陆框居中
	login.center(352,280); 	//刷新时居中
	login.resize(function(){		//浏览器视口变化触发
		if (login.css('display') == 'block') {		//判断登陆框是否显示
			screen.lock();
		}
	});	
	
	//注册框居中
	reg.center(450,300); 	
	reg.resize(function(){		//浏览器视口变化触发
		if (reg.css('display') == 'block') {		//判断注册框是否显示
			screen.lock();
		}
	});	
	//登陆框、注册框拖拽
	login.drag($('#login h2').first());	//可传多个拖拽的点
	reg.drag($('#reg h2').first());		//可传多个拖拽的点
	
	
	
	/**表单验证********/


	//刷新时初始化表单
	$('form').first().reset();
	$('form').ge(1).reset();
	//focus,blur
	//用户名验证
	$('form').eq(1).form('user').bind('focus',function(){			
		$('#reg .info_user').show();
		$('#reg .error_user').hide();
		$('#reg .succ_user').hide();
	}).bind('blur',function(){
		if(trim($(this).value())==''){
			$('#reg .info_user').hide();		
			$('#reg .error_user').hide();
			$('#reg .succ_user').hide();
		}else if(!check_user()){
			$('#reg .error_user').show();
			$('#reg .info_user').hide();
			$('#reg .succ_user').hide();
		}else{
			$('#reg .succ_user').show();
			$('#reg .error_user').hide();
			$('#reg .info_user').hide();
		}
	});

	
	function check_user(){				//检测函数
		var flag=true;
		if(!/[\w]{2,20}/.test(trim($('form').eq(1).form('user').value()))) {
			$('#reg .error_user').html('输入不合法，请重新输入！');
			return false;
		}else{
			$('#reg .info_user').hide();
			$('#reg .error_user').hide();
			$('#reg .succ_user').hide();
			$('#reg .loading').show();
			ajax({
				method:'post',
				url:'CheckName',
				data:$('form').eq(1).serialize(),
				success:function(text){
					$('#reg .loading').hide();
					if(text==1){
						$('#reg .error_user').html('用户名被占用！');
						flag=false;
					}else{
						flag=true;
					}
				},
				async:false 	//这里不采用异步
			});	
			return flag;
		}
		//return flag;
	}

	//密码验证
	$('form').eq(1).form('pass').bind('focus',function(){
		$('#reg .info_pass').show();
		$('#reg .error_pass').hide();
		$('#reg .succ_pass').hide();
	}).bind('blur',function(){
		if(trim($(this).value())==''){				//未输入
			$('#reg .info_pass').hide();
			$('#reg .error_pass').hide();
			$('#reg .succ_pass').hide();
		}else{
			if(check_pass()){					//密码格式正确
				$('#reg .succ_pass').show();
				$('#reg .error_pass').hide();
				$('#reg .info_pass').hide();
			}else{									//密码不合法
				$('#reg .error_pass').show();
				$('#reg .info_pass').hide();
				$('#reg .succ_pass').hide();
			}									
		}
		
	});
//密码强度验证
	$('form').eq(1).form('pass').bind('keyup',function(){
		check_pass();
	});
	
	//密码验证函数
	function check_pass(){
		var value=trim($('form').eq(1).form('pass').value());
		var value_length=value.length;
		var code_length=0;  		//字符种类个数
		var flag=false;    			//密码是否合格
		
		//必须6-20位之间
		if(value_length>=6&&value_length<=20){
			$('#reg .info_pass .q1').html('●').css('color','green');
		}else{
			$('#reg .info_pass .q1').html('○').css('color','#666');
		}
		
		//只包含字母、数字和非空字符
		if(value_length>0&&!/\s/.test(value)){
			$('#reg .info_pass .q2').html('●').css('color','green');
		}else{
			$('#reg .info_pass .q2').html('○').css('color','#666');
		}
		
		//满足两种字符以上
		if(/[0-9]/.test(value)){
			code_length++;
		}
		if(/[a-z]/.test(value)){
			code_length++;
		}
		if(/[A-Z]/.test(value)){
			code_length++;
		}
		if(/[^0-9a-zA-Z]/.test(value)){
			code_length++;
		}
		
		if(code_length>=2){
			$('#reg .info_pass .q3').html('●').css('color','green');
		}else{
			$('#reg .info_pass .q3').html('○').css('color','#666');
		}
		
		//安全级别: 高：大于等于10个字符，三种
				//	中：大于等于8 个字符，两种
				//	低：大于等于1 个字符
				
		if(value_length>=10&&code_length>=3){
			$('#reg .info_pass .s1').css('color','green');
			$('#reg .info_pass .s2').css('color','green');
			$('#reg .info_pass .s3').css('color','green');
			$('#reg .info_pass .s4').html('高').css('color','green');
		}else if(value_length>=8&&code_length>=2){
			$('#reg .info_pass .s1').css('color','#f60');
			$('#reg .info_pass .s2').css('color','#f60');
			$('#reg .info_pass .s3').css('color','#ccc');
			$('#reg .info_pass .s4').html('中').css('color','#f60');
		}else if(value_length>=1){
			$('#reg .info_pass .s1').css('color','maroon');
			$('#reg .info_pass .s2').css('color','#ccc');
			$('#reg .info_pass .s3').css('color','#ccc');
			$('#reg .info_pass .s4').html('低').css('color','maroon');
		}else{
			$('#reg .info_pass .s1').css('color','#ccc');
			$('#reg .info_pass .s2').css('color','#ccc');
			$('#reg .info_pass .s3').css('color','#ccc');
			$('#reg .info_pass .s4').html('');
		}
		
		if(value_length>=6&&value_length<=20&&!/\s/.test(value)&&code_length>=2)flag=true;
		return flag;
		
	}


//密码确认验证	
	$('form').eq(1).form('notpass').bind('focus',function(){
		$('#reg .info_notpass').show();
		$('#reg .error_notpass').hide();
		$('#reg .succ_notpass').hide();
	}).bind('blur',function(){
		if(trim($(this).value())==''){				
			$('#reg .info_notpass').hide();
			$('#reg .error_notpass').hide();
			$('#reg .succ_notpass').hide();
		}else if(check_notpass()){
			$('#reg .info_notpass').hide();
			$('#reg .error_notpass').hide();
			$('#reg .succ_notpass').show();
		}else{
			$('#reg .info_notpass').hide();
			$('#reg .error_notpass').show();
			$('#reg .succ_notpass').hide();
		}
		
	});
	//密码确认函数
	function check_notpass(){
		if(trim($('form').eq(1).form('notpass').value())==trim($('form').eq(1).form('pass').value())) return true;
	}
	

//提交
	$('form').eq(1).form('sub').click(function(){
		var flag=true;
		if(!check_user()){
			flag=false;
			$('#reg .error_user').show();
		}
		if(!check_pass()){
			flag=false;
			$('#reg .error_pass').show();
		}
		if(!check_notpass()){
			flag=false;
			$('#reg .error_notpass').show();
		}
		
		if(flag){
			var _this=this;
			$('#loading').show().center(200,40);
			$('#loading p').html('正在提交注册中...');
			_this.disabled=true;
			$(_this).css('backgroundPosition','right');

			ajax({
				method:'post',
				url:'RegisterServlet',
				data:$('form').eq(1).serialize(),
				success:function(text){
					if(text==1){
						$('#loading').hide();
						$('#success').show().center(200,40);
						$('#success p').html('注册成功，请登录..');
						$('#reg .succ').hide();
						setTimeout(function(){
							$('#success').hide();
							$('form').ge(1).reset();
							reg.hide();
							_this.disabled=false;
							$(_this).css('backgroundPosition','left');
							screen.animate({		//同时遮罩隐藏
								attr:'o',
								target:0,
								t:30,
								step:10,
								fn:function(){
									screen.unlock();	//透明动画结束后取消锁屏
								}
							});			
						},3000);
					}
				},
				async:true
			});
		}
		//alert($('form').eq(1).serialize().pass);
	});

//登录框验证
	$('#login .submit input').click(function(){
		if(!/[\w]{2,20}/.test(trim($('form').eq(0).form('user').value()))){
			$('#login .warn').html('登录失败：用户名或密码不合法！');			
		}else{
			$('#login .warn').html('');
			var _this=this;
			$(_this).css('backgroundPosition','right');
			$('#loading p').html('正在尝试登录..');
			$('#loading').show().center(200,40);
			_this.disabled=true;
			
		
			ajax({
				method:'post',
				url:'LoginServlet',
				data:$('form').eq(0).serialize(),
				success:function(text){
					$('#loading').hide();
					if(text==1){	//失败,用户不存在
						$('#login .warn').html('登录失败：用户名或密码不正确！');
					}else{	//成功
						$('#login .warn').html('');
						$('#success').show().center(200,40);
						$('#success p').html('登录成功！');
						//cookie 实现
						setCookie('user',trim($('form').eq(0).form('user').value()));
						setTimeout(function(){
							$('#success').hide();
							$('form').ge(0).reset();
							login.hide();
							screen.animate({		//同时遮罩隐藏
								attr:'o',
								target:0,
								t:30,
								step:10,
								fn:function(){
									screen.unlock();	//透明动画结束后取消锁屏
								}
							});	
							$('#header .nologin').hide();
							$('#header .userinfo').show();	
							$('#header .info1').html(getCookie('user')+',您好！');
						},1500);
					}
					_this.disabled=false;
					$(_this).css('backgroundPosition','left');
					
				
				},
				async:true
			});
		}
	});
	
	//刷新时判断是否登录
	if(getCookie('user')!=null){
		$('#header .nologin').hide();
		$('#header .userinfo').show();	
		$('#header .info1').html(getCookie('user')+',您好！');
	}
	
	$('header .exit').click(function(){
		if(confirm('确定退出?')==false){
			return false;
		}else{
			setCookie('user','',new Date(0));
			$('#header .nologin').show();
			$('#header .userinfo').hide();	
		}
	});	
	
});