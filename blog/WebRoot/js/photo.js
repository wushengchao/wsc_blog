$(function(){
	/**延迟加载**********/
	/*	
		获取属性值：alert($('.wait_load').eq(0).attr('xsrc'));
		问题1：xsrc替换src
			for(var i=0;i<$('.wait_load').length();i++)$('.wait_load').eq(i).attr('src',$('.wait_load').eq(i).attr('xsrc'));
		问题2：获取图片元素到最外层顶点距离
			alert(offsetTop($('.wait_load').first()));
		
		问题3：获取页面可视区域的最低点的位置
			alert(getInner().height+getScroll().top);
	*/
	
	var wait_load=$('.wait_load');		//防止变卡，但会使length变1(下面已解决)
	wait_load.opacity(0);
	$(window).bind('scroll',_wait_load);	//滚动条变化
	$(window).bind('resize',_wait_load);	//窗口变化
	
	//到达图片位置时，显示图片函数
	function _wait_load(){
		setTimeout(function(){
			for(var i=0;i<wait_load.length();i++){
				var _this=wait_load.ge(i);					//防止length变为1，使后面几张不能替换,不懂？？？？？？？？？？？
				if(getInner().height+getScroll().top>=offsetTop(_this)){	//拖到图片位置是开始显示
					$(_this).attr('src',$(_this).attr('xsrc')).animate({
						attr:'o',
						target:100,
						t:30,
						step:10
					});	
				}	
			}	
		},100);
	}
	
/**图片弹窗***************/
	//开关		
	var photo_big=$('#photo_big')
	wait_load.click(function(){	//点击显示
		photo_big.show().center(620,511);  //居中显示
		screen.lock().animate({
			attr:'o',
			target:30,
			t:50,
			step:10
		});		//同时遮罩显示,透明度渐变
		
		var temp_img=new Image();
		$(temp_img).bind('load',function(){
			$('#photo_big .big img').attr('src',temp_img.src).animate({
				attr:'o',
				target:100,
				t:30,
				step:10
			}).css('top',0).css('width','600px').css('height','440px').opacity(0);		
		});
		
		//IE必须写在load事件后面
		temp_img.src=$(this).attr('bigsrc');
		
		//前后图片加载
		var children=this.parentNode.parentNode;
		prev_next_img(children);
	});
	$('#photo_big .close').click(function(){	//点击close关闭
		photo_big.hide();		//隐藏
		screen.animate({		//同时遮罩隐藏
			attr:'o',
			target:0,
			t:30,
			step:10,
			fn:function(){
				screen.unlock();	//透明动画结束后取消锁屏
			}
		});	
		
		//将图片换为load
		$('#photo_big .big img').attr('src','images/loading.gif').css('top','190px').css('width','auto').css('height','auto')
	});
	
	//居中
	photo_big.center(632,511); 	
	photo_big.resize(function(){		//浏览器视口变化触发
		if (photo_big.css('display') == 'block') {		//判断是否显示
			screen.lock();
		}
	});	
	//拖拽
	photo_big.drag($('#photo_big h2').first());	//可传多个拖拽的点
	
	//图片加载
	/*
	$('#photo_big .big img').attr('src','https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1353893696,3401154138&fm=26&gp=0.jpg').animate({
		attr:'o',
		target:100,
		t:30,
		step:10
	}).css('top',0).css('width','600px').css('height','440px').opacity(0);
	
	//创建一个临时图片对象
	//alert($('#photo_big .big img').first());
	//alert(new Image());
	
	var temp_img=new Image();	//临时区域图片对象
	//src属性可在后台加载
	
	$(temp_img).bind('load',function(){
		$('#photo_big .big img').attr('src',temp_img.src).animate({
			attr:'o',
			target:100,
			t:30,
			step:10
		}).css('top',0).css('width','600px').css('height','440px').opacity(0);		
	});
	
	//IE必须写在load事件后面
	temp_img.src='https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1353893696,3401154138&fm=26&gp=0.jpg';
	*/
	
	//图片鼠标划过
	$('#photo_big .big .left').hover(function(){	//左
		$('#photo_big .big .sl').animate({
			attr:'o',
			target:50,
			t:30,
			step:10
		});
	},function(){
		$('#photo_big .big .sl').animate({
			attr:'o',
			target:0,
			t:30,
			step:10
		});
	});
	$('#photo_big .big .right').hover(function(){	//右
		$('#photo_big .big .sr').animate({
			attr:'o',
			target:50,
			t:30,
			step:10
		});
	},function(){
		$('#photo_big .big .sr').animate({
			attr:'o',
			target:0,
			t:30,
			step:10
		});
	});
	
	//图片上一张
	$('#photo_big .big .left').click(function(){
		$('#photo_big .big img').attr('src','images/loading.gif').css('width','32px').css('height','32px').css('top','190px');
		
		var current_img=new Image()
		
		$(current_img).bind('load',function(){		//加载完成后执行
			$('#photo_big .big img').attr('src',$(this).attr('src')).animate({
				attr:'o',
				target:100,
				t:30,
				step:10
			}).opacity(0).css('width','600px').css('height','450px').css('top',0);
		});
		
		current_img.src=$(this).attr('src');//加载图片
		
		//alert($('#photo_big .big img').attr('index'));	当前图片索引
		//alert($('#photo dl dt img').ge($('#photo_big .big img').attr('index'))); 当前图片的小图元素
		//上一张小图元素
		var children=$('#photo dl dt img').ge(prevIndex($('#photo_big .big img').attr('index'),$('#photo').first())).parentNode.parentNode;
		//更改前后路径,及当前图片索引
		prev_next_img(children);
		
	});
	
	
	//图片下一张
	$('#photo_big .big .right').click(function(){
		
		$('#photo_big .big img').attr('src','images/loading.gif').css('width','32px').css('height','32px').css('top','190px');
		
		var current_img=new Image()
		
		$(current_img).bind('load',function(){
			$('#photo_big .big img').attr('src',$(this).attr('src')).animate({
				attr:'o',
				target:100,
				t:30,
				step:10
			}).opacity(0).css('width','600px').css('height','450px').css('top',0);
		});
		
		current_img.src=$(this).attr('src');
		
		var children=$('#photo dl dt img').ge(nextIndex($('#photo_big .big img').attr('index'),$('#photo').first())).parentNode.parentNode;
		
		prev_next_img(children);	//更改前后路径
	
	});
	
	//保存前后图片路径，及当前图片索引 函数
	function prev_next_img(children){
		var prev=prevIndex($(children).index(),children.parentNode);
		var next=nextIndex($(children).index(),children.parentNode);
		
		var pre_img=new Image();
		var next_img=new Image();
		//提前加载
		pre_img.src=$('#photo dl dt img').eq(prev).attr('bigsrc');
		next_img.src=$('#photo dl dt img').eq(next).attr('bigsrc');
		//保存前后图片路径到src中
		$('#photo_big .big .left').attr('src',pre_img.src);
		$('#photo_big .big .right').attr('src',next_img.src);
		//保存当前图片索引
		$('#photo_big .bog img').attr('index',$(children).index());
		//下标
		$('#photo_big .big .index').html($(children).index()+1+'/'+children.parentNode.children.length);
	}
	
});