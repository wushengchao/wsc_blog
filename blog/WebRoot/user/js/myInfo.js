$(function(){
	var reg=$('#reg');			//个人信息编辑框
	var screen=$('#screen');	
	reg.center(630,550);  //居中显示
	$('#section .write').click(function(){	//点击显示
		reg.show().center(630,550);  //居中显示
		$('form').first().reset();
		$('#reg .error').hide();
		$('#reg .succ').hide();
		screen.lock().animate({
			attr:'o',
			target:50,
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

	reg.drag($('#reg h2').first());		//可传多个拖拽的点

	reg.resize(function(){		//浏览器视口变化触发
		if (reg.css('display') == 'block') {		//判断注册框是否显示
			screen.lock();	//铺满
		}
	});	



/**表单验证*********/




	//刷新时初始化表单
	$('form').first().reset();
	//$('form').ge(1).reset();
	//focus,blur
	


//电子邮箱验证
	$('form').eq(0).form('email').bind('focus',function(){
		//补全界面
		if($(this).value().indexOf('@')==-1){
			$('#reg .all_email').show();
		}

	}).bind('blur',function(){
		$('#reg .all_email').hide();	//补全界面隐藏

	});
	
	function check_email(){		//邮箱格式检查
		if(/^[\w\-\.]+@[\w\-]+(\.[a-zA-Z]{2,4}){1,2}$/.test(trim($('form').eq(0).form('email').value())))return true;
	}
	
	//电子邮箱补全系统键入
	$('form').eq(0).form('email').bind('keyup',function(e){
		if($(this).value().indexOf('@')==-1){
			$('#reg .all_email').show();
			$('#reg .all_email li span').html(trim($(this).value()));		
		}else{
			$('#reg .all_email').hide();
		}
		//键盘选择补全
		var length=$('#reg .all_email li').length();
		
		$('#reg .all_email li').css('background','none');	//默认颜色
		$('#reg .all_email li').css('color','#666');
		if(e.keyCode==40){									//键盘向下
			if(this.index==undefined||this.index>=length-1){
				this.index=0;
			}else{
				this.index++;
			}	
			$('#reg .all_email li').eq(this.index).css('background','#e5edf2');
			$('#reg .all_email li').eq(this.index).css('color','#369');
		}else if(e.keyCode==38){									//键盘向上
			if(this.index==undefined||this.index<=0){
				this.index=length-1;
			}else{
				this.index--;
			}	
			$('#reg .all_email li').eq(this.index).css('background','#e5edf2');
			$('#reg .all_email li').eq(this.index).css('color','#369');
		}else if(e.keyCode==13){									//回车
			$(this).value($('#reg .all_email li').eq(this.index).text());
			$('#reg .all_email').hide();
			this.index=undefined;
		}else{
			this.index=undefined;	//按其他键时让她从头开始
		}
	});
	
	//电子邮箱点击补全  
	//click会在blur之后，导致blur使all_email隐藏，导致click无法触发，使用mousedown
	$('#reg .all_email li').bind('mousedown',function(){
		$('form').eq(0).form('email').value($(this).text());
		
	});
	
	//电子邮箱补全系统鼠标移入移出效果
	$('#reg .all_email li').hover(function(){
		$(this).css('background','#e5edf2');
		$(this).css('color','#369');
	},function(){
		$(this).css('background','none');
		$(this).css('color','#666');
	});
	
//年、月、日选择
	var year=$('form').eq(0).form('year');
	var month=$('form').eq(0).form('month');
	var day=$('form').eq(0).form('day');
	
	var day30=[4,6,9,11];
	var day31=[1,3,5,7,8,10,12];
	
	//注入年******
	for(var i=1950;i<=2017;i++){
		year.first().add(new Option(i,i),undefined);     //add()为表单操作方法，IE不能用null，用undefined
	}
	//注入月******
	for(var i=1;i<=12;i++){
		month.first().add(new Option(i,i),undefined);
	}
	
	
	//注入日*******
	
	//年改变
	year.bind('change',select_day);

	//月改变
	month.bind('change',select_day);
	

	//日变化函数
	function select_day(){
		if(year.value()!=0&&month.value()!=0){
			
			day.first().options.length=1;	//清空之前注入，防止重复
			
			var cur_day=0;			//不确定的日
			
			if(inArray(day31,parseInt(month.value()))){
				cur_day=31;		//31天
				
			}else if(inArray(day30,parseInt(month.value()))){
				cur_day=30;	//30天
			}else{							//二月
				var year_value=parseInt(year.value());
				if((year_value%4==0&&year_value%100!=0)||year_value%400==0){
					cur_day=29;
				}else{
					cur_day=28;
				}
				
			}
			
			for(var i=1;i<=cur_day;i++){		//注入天数cur_day
					day.first().add(new Option(i,i),undefined);
			}
		}else{
			day.first().options.length=1; 		//清空之前注入
		}
	}
	
	//日改变
	day.bind('change',function(){
		if(check_birthday()){
			$('#reg .error_birthday').hide();
		}	
	});
	function check_birthday(){	//判断年月日是否已选
		if(year.value()!=0&&month.value()!=0&&day.value()!=0)return true;
	}
	
//注册框备注	
	//键入内容时改变剩余字数
	$('form').eq(0).form('ps').bind('keyup',check_ps).bind('paste',function(){
		setTimeout(check_ps,50);	//延迟50毫秒检查字数，避免粘贴事件在检查之后，导致检测不到
	});
	
	//清尾
	$('#reg .ps .clear').click(function(){
		$('form').eq(0).form('ps').value($('form').eq(0).form('ps').value().substring(0,200));
		check_ps();
	});
	
	//检查字数函数
	function check_ps(){
		var num=200-$('form').eq(0).form('ps').value().length;
		if(num>=0){
			$('#reg .ps').eq(0).show();
			$('#reg .ps .num').eq(0).html(num);
			$('#reg .ps').eq(1).hide();
			return true;
		}else{
			$('#reg .ps').eq(0).hide();
			$('#reg .ps .num').eq(1).html(Math.abs(num)).css('color','red');
			$('#reg .ps').eq(1).show();
			return false;
		}
	}

//注册表单序列化serialize
	//base_form.js 

//提交
	$('form').eq(0).form('sub').click(function(){
		var flag=true;
		
		if(!check_ps()){
			alert("自我介绍超出200字！");
			flag=false;
		}
		if(flag){
			
			//$('form').first().submit(); 普通提交，下面ajax提交
			var _this=this;
			$('#loading').show().center(200,40);
			$('#loading p').html('正在提交信息中...');
			_this.disabled=true;
			$(_this).css('backgroundPosition','right');
			//ajax
			ajax({
				method:'post',
				url:'UpdateUserInfo',
				data:$('form').eq(0).serialize(),
				success:function(text){
					if(text==1){
						$('#loading').hide();
						$('#success').show().center(200,40);
						$('#success p').html('信息更改成功');
						$('#reg .succ').hide();
						setTimeout(function(){
							$('#success').hide();
							$('form').first().reset();
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
						},1500);
					}
				},
				async:true
			});
		}
	});
	

});