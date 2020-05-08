$(function(){
	/**轮播器********/
	//document.getElementById('banner').scrollIntoView();
	//轮播器初始化
	//$('#banner img').hide();
	//$('#banner img').eq(0).show();
	$('#banner img').opacity(0);
	$('#banner img').eq(0).opacity(100);
	$('#banner .banner_img ul li').eq(0).css('color','#333');
	//$('#banner strong').html($('#banner img').eq(0).attr('alt'));

	//轮播计数器
	var banner_index=1;
	//轮播器种类
	var banner_type=1;	//1为透明度变换，2为位置变换
	
	//自动轮播器
	var banner_timer=setInterval(banner_fn,3000);
	
	//手动轮播器
	$('#banner .banner_img ul li').hover(function(){
		banner_index=$(this).index();
		if($(this).css('color') != 'rgb(51, 51, 51)'&&$(this).css('color')!='#333'){	//已被选定
			banner();
		}
		clearInterval(banner_timer);	
	},function(){
		banner_index++;			//要加一，否则会停留较久, 讲义代码中可保存前一个的节点，这里影响不大
		banner_timer=setInterval(banner_fn,3000);
	});

	//轮播函数
	function banner(){
		//$('#banner img').hide();
		//$('#banner img').eq(banner_index).show();
		if(this.last_index==undefined)last_index=0;
		$('#banner .banner_img ul li').css('color','#999');
		$('#banner .banner_img ul li').eq(banner_index).css('color','#333');
		//$('#banner strong').html($('#banner img').eq(banner_index).attr('alt'));
		if(banner_type==1){
			$('#banner img').eq(this.last_index).show().animate({
				attr:'o',
				start:100,
				target:0,
				t:100,
				step:5
			});
			$('#banner img').eq(banner_index).animate({
				attr:'o',
				start:0,
				target:100,
				t:100,
				step:5
			});
			$("#banner").css("background",$('#banner img').eq(banner_index).attr('bg_color'));
		}else if(banner_type==2){
			//$('#banner img').css('zIndex',0);
			$('#banner img').eq(this.last_index).show().css('zIndex',1).animate({
				attr:'y',
				//start:0, IE报错(base.js注释掉了)
				target:400,
				t:50,
				step:5
			});
			$('#banner img').eq(banner_index).opacity(100).css('top','-400px').css('zIndex',2).animate({
				attr:'y',
				target:0,
				t:50,
				step:5
			});
			$("#banner").css("background",$('#banner img').eq(banner_index).attr('bg_color'));
		}
		this.last_index=banner_index;
	}
	function banner_fn(){
		if(banner_index>=$('#banner img').length())banner_index=0;
		banner();
		banner_index++;
	}

/***博文类别显示*********/
	
	$("#banner .type li").hover(function(){
		
		var type_index=$(this).index();
		$("#banner .type li a").eq(type_index-1).css("background","rgba(255,255,255,1)").css("color","#333");
		$("#banner .sort_"+type_index).show().animate({
			attr:'o',
			target:100,
			t:50,
			step:10
		});

	},function(){
		var type_index=$(this).index();
		$("#banner .type li a").eq(type_index-1).css("background","rgba(0,0,0,0.5").css("color","#fff");
		//$("#banner .sort_"+type_index).hide();
		$("#banner .sort_"+type_index).animate({
			attr:'o',
			target:0,
			t:50,
			step:10,
			fn:function(){
				$("#banner .sort_"+type_index).hide();
			}
		});
		
	});
});