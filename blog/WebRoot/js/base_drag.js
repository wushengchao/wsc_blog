//这里是拖拽功能的插件
$().extend('drag',function(){
	var tags=arguments;
	for(var i=0;i<this.elements.length;i++){
		addEvent(this.elements[i],'mousedown',function(e){
			if(trim(this.innerHTML).length==0){
				e.preventDefault();	//阻止默认行为，防止低版本火狐拖动异常,但会造成无法点击输入框		
			};
			var _this=this;
			var diffX=e.clientX-_this.offsetLeft;
			var diffY=e.clientY-_this.offsetTop;
			//自定义拖拽区域
			var flag=false;
			for(var i=0;i<tags.length;i++){
				if(e.target==tags[i]){
					flag=true;
					break;
				}
			}
			if(flag){
				//添加鼠标事件
				addEvent(document,'mousemove',move);
				addEvent(document,'mouseup',up);				
			}else{
				//移除鼠标事件
				removeEvent(document,'mousemove',move);
				removeEvent(document,'mouseup',up);
			};

			//事件函数move
			function move(e){
				var left=e.clientX-diffX;
				var top=e.clientY-diffY;
				if(left<getScroll().left){				//使拖拽窗口不超过浏览器视口
					left=getScroll().left;
				}else if(left>getInner().width-_this.offsetWidth+getScroll().left){
					left=getInner().width-_this.offsetWidth+getScroll().left;
				}
				if(top<getScroll().top){
					top=getScroll().top;
				}else if(top>getInner().height-_this.offsetHeight+getScroll().top){
					top=getInner().height-_this.offsetHeight+getScroll().top;
				}
				_this.style.left=left+'px';
				_this.style.top=top+'px';
				if(typeof _this.setCapture!='undefined'){
					_this.setCapture();		//解决IE鼠标超出视口，放down事件会造成无法点击输入框，所以放move
				}
			}
			//事件函数up
			function up(e){
				removeEvent(document,'mousemove',move)
				removeEvent(document,'mouseup',up)
				if(typeof _this.releaseCapture!='undefined'){
					_this.releaseCapture();		//解决IE鼠标超出视口
				}
			}
			
		});
	}
	return this;	
});