/*封装库******/

/*1.Element节点获取**/
/*
	var base={

		getId:	function(id){
					return document.getElementById(id);
				},
		getName:function(name){
					return document.getElementsByName(name);
				},
		getTagName:function(tagName){
					return document.getElementsByTagName(tagName);
				}
	}
*/


/*2.连缀**********/

//书本第六章构造函数
function $(args){
	return new Base(args); //防止只有一个base，不同元素互相干扰，使用$()创建对象
}
function Base(args){
	this.elements=[];	//获取节点的数组 ,这个不能放外面,否则会导致不同元素间干扰
	if(typeof args=='string'){
		//css模拟
		if(args.indexOf(' ')!=-1){
			var elements=args.split(' '); //把节点拆开保存到数组
			var childElements=[];		//存放临时节点对象数组，解决被覆盖
			var node=[];      //存放父节点
			for(var i=0;i<elements.length;i++){
				if(node.length==0)node.push(document);	//没有父节点时，默认document
				switch(elements[i].charAt(0)){
				case'#':
					childElements=[];	//清理临时节点
					childElements.push(this.getId(elements[i].substring(1)));
					node=childElements;	//保存父节点
					break;
				case'.':
					childElements=[];	//清理临时节点
					for(var j=0;j<node.length;j++){
						var temps=this.getClass(elements[i].substring(1),node[j]);
						for(var k=0;k<temps.length;k++){
							childElements.push(temps[k]);
						}
					}
					node=childElements;
					break;
				default:
					childElements=[];	//清理临时节点
					for(var j=0;j<node.length;j++){
						var temps=this.getTagName(elements[i],node[j]);
						for(var k=0;k<temps.length;k++){
							childElements.push(temps[k]);
						}
					}
					node=childElements;
				}
			}
			this.elements=childElements;
		}else{
			//find模拟
			switch(args.charAt(0)){
				case'#':
					this.elements.push(this.getId(args.substring(1)));
					break;
				case'.':
					this.elements=this.getClass(args.substring(1));
					break;
				default:
					this.elements=this.getTagName(args);
			}	
		}
		
	}else if(typeof args=='object'){	//this、window等？
		if(args!=undefined){	//?
			if(args.length>1){
				this.elements=args;
			}else{
				this.elements[0]=args;	
			}
		}		
	}else if(typeof args=='function'){
		addDomloaded(args);		//DOM加载(tool里)代替window.onload
	}

	
}
/***元素获取*******************/

//通过id获取
Base.prototype.getId=function(id){
	//this.elements.push(document.getElementById(id));
	return document.getElementById(id);
};
//通过name获取
Base.prototype.getName=function(name){
	var names=document.getElementsByName(name);
	for(var i=0;i<names.length;i++){
		this.elements.push(names[i]);
	}
	return this;
};
//通过tagName获取
Base.prototype.getTagName=function(TagName,parentNode){	//两个参数在find()用
	var node=null;
	var temps=[]
	if(parentNode!=undefined){	//传入两个参数时，操作parentName底下的class
		node=parentNode	
	}else{
		node=document;
	}
	var tags=node.getElementsByTagName(TagName);
	for(var i=0;i<tags.length;i++){
		temps.push(tags[i]);
	}
	return temps;
};	
//通过class获取
Base.prototype.getClass=function(className,parentNode){
	var node=null;
	var temps=[]
	if(parentNode!=undefined){	//传入两个参数时，操作parentName底下的class
		node=parentNode	
	}else{
		node=document;
	}
	var all=node.getElementsByTagName("*");
	for(var i=0;i<all.length;i++){
		//if(all[i].className==className){	//不能获取多个class名的元素
		if((new RegExp('(\\s|^)'+className+'(\\s|$)')).test(all[i].className)){
			temps.push(all[i]);
		}
	}
	return temps;
};
//设置CSS选择器的子节点
Base.prototype.find=function(str){
	var childElements=[];
	for(var i=0;i<this.elements.length;i++){
		switch(str.charAt(0)){
			case'#':
				childElements.push(this.getId(str.substring(1)));
				break;
			case'.':
				/*
				var all=this.elements[i].getElementsByTagName("*");
				for(var j=0;j<all.length;j++){
					if(all[j].className==str.substring(1)){
						childElements.push(all[j]);
					}
				}*/
		
				var temps=this.getClass(str.substring(1),this.elements[i]);
				for(var j=0;j<temps.length;j++){
					childElements.push(temps[j]);
				}
				break;
			default:
				//var tags=this.elements[i].getElementsByTagName(str);
				var tags=this.getTagName(str,this.elements[i]);
				for(var j=0;j<tags.length;j++){
					childElements.push(tags[j]);
				}
		}
	}
	this.elements=childElements;
	return this;
}
//获取某一个节点,返回节点对象  如：$().getClass('pox').getElement(0)
Base.prototype.ge=function(num){
	return this.elements[num];
}
//获取首个节点,返回节点对象
Base.prototype.first=function(){
	return this.elements[0];
}
//获取最后一个节点,返回节点对象
Base.prototype.last=function(){
	return this.elements[this.elements.length-1];
}
//获取某组节点数量
Base.prototype.length=function(){
	return this.elements.length;
}
//获取某一个节点,返回Base对象
Base.prototype.eq=function(num){
	var element=this.elements[num];
	this.elements=[]; 		//清空
	this.elements.push(element);
	return this;
}

//获取当前节点的下一个元素节点
Base.prototype.next=function(){
	for(var i=0;i<this.elements.length;i++){
		this.elements[i]=this.elements[i].nextSibling;
		if(this.elements[i]==null)throw new Error("找不到下一个同级元素节点");
		if(this.elements[i].nodeType==3)this.next(); //3为空白
	}
	return this;
}
//获取当前节点的上一个元素节点
Base.prototype.prev=function(){
	for(var i=0;i<this.elements.length;i++){
		this.elements[i]=this.elements[i].previousSibling;
		if(this.elements[i]==null)throw new Error("找不到上一个同级元素节点");
		if(this.elements[i].nodeType==3)this.prev(); //3为空白
	}
	return this;
}

/***各种设置、功能实现**********************/

//css设置
//为什么用style属性用[],不用'.'?????普通获取用'.',貌似是因为参数要用[]
Base.prototype.css=function(attr,value){	
	for(var i=0;i<this.elements.length;i++){
		if(arguments.length==1){
			if(typeof getStyle(this.elements[i],attr)=='number'){
				return getStyle(this.elements[i],attr)+'px';
			}else{
				return getStyle(this.elements[i],attr);	
			}
		}
		this.elements[i].style[attr]=value; 
	}
	return this;
}
//设置link或style中的css规则 P318 (不用)
Base.prototype.addRule=function(num,selectorText,cssText,position){
	var sheet=document.styleSheets[num];
	insertRule(sheet,selectorText,cssText,position);
	return this;
}

//移除link或style中css规则	(不用)
Base.prototype.removeRule=function(num,index){
	var sheet=document.styleSheets[num];
	deleteRule(sheet,index);
	return this;
}

//设置表单字段元素
Base.prototype.form=function(name){
	for(var i=0;i<this.elements.length;i++){
		this.elements[i]=this.elements[i][name];
	}
	return this;
}

//设置表单字段内容获取，修改
Base.prototype.value=function(str){			
	for(var i=0;i<this.elements.length;i++){
		if(arguments.length==0){
			return this.elements[i].value;	//没传参时，获取innerHTML
		}
		this.elements[i].value=str;
	}
	return this;	
}

//获取某一节点的属性
Base.prototype.attr=function(attr,value){
	for(var i=0;i<this.elements.length;i++){
		if(arguments.length==1){
			return this.elements[i].getAttribute(attr);
		}else if(arguments.length==2){
			this.elements[i].setAttribute(attr,value);
		}	
	}
	return this;
}

//获取某一个节点在整个节点组中是第几个索引
Base.prototype.index=function(){
	var children=this.elements[0].parentNode.children;
	for(var i=0;i<children.length;i++){
		if(this.elements[0]==children[i]){
			return i;
		}
	}
}

//设置某一节点透明度
Base.prototype.opacity=function(num){
	for(var i=0;i<this.elements.length;i++){
		this.elements[i].style.opacity=num/100;
		this.elements[i].style.filter='alpha(opacity='+num+')';
	}
	return this;
}

//设置innerText修改
Base.prototype.text=function(str){			
	for(var i=0;i<this.elements.length;i++){
		if(arguments.length==0){
			return getInnerText(this.elements[i]);	//没传参时，获取innerHTML
		}
		setInnerText(this.elements[i],str);
	}
	return this;	
}

//设置innerHTML修改
Base.prototype.html=function(str){			
	for(var i=0;i<this.elements.length;i++){
		if(arguments.length==0){
			return this.elements[i].innerHTML;	//没传参时，获取innerHTML
		}
		this.elements[i].innerHTML=str;
	}
	return this;	
}


//添加class
Base.prototype.addClass=function(className){
	for(var i=0;i<this.elements.length;i++){
		if(!hasClass(this.elements[i],className)){
			this.elements[i].className+=' '+className;			
		}
	}
	return this;
}
//移除class
Base.prototype.removeClass=function(className){
	for(var i=0;i<this.elements.length;i++){
		if(hasClass(this.elements[i],className)){
			this.elements[i].className=this.elements[i].className.replace(new RegExp('(\\s|^)'+className+'(\\s|$)'),' ');
		}
	}
	return this;
}

//设置点击切换方法
Base.prototype.toggle=function(){
	for(var i=0;i<this.elements.length;i++){
		(function(element,arguments){
			var count=0;
			var args=arguments;
			addEvent(element,'click',function(){
				args[count++%args.length].call(this);
			});
		})(this.elements[i],arguments);
	}
	return this;
}



//设置显示
Base.prototype.show=function(){
	for(var i=0;i<this.elements.length;i++){
		this.elements[i].style.display='block';
	}
	return this;
}
//设置隐藏
Base.prototype.hide=function(){
	for(var i=0;i<this.elements.length;i++){
		this.elements[i].style.display='none';
	}
	return this;
}

//设置物体居中
Base.prototype.center=function(width,height){
	var left=(getInner().width-width)/2+getScroll().left;
	var top=(getInner().height-height)/2+getScroll().top;
	for(var i=0;i<this.elements.length;i++){
		this.elements[i].style.top=top+"px";
		this.elements[i].style.left=left+"px";
	}

}

//锁屏功能
Base.prototype.lock=function(){
	for(var i=0;i<this.elements.length;i++){
		this.elements[i].style.width=getInner().width+getScroll().left+'px';
		this.elements[i].style.height=getInner().height+getScroll().top+'px';
		this.elements[i].style.display="block";
		//隐藏多余的部分，隐藏滚动条
		parseFloat(sys.firefox)<4?document.body.style.overflow='hidden':document.documentElement.style.overflow='hidden';	
		/* 防止IE在登陆框意外区域往下拖，但会造成文本框不能选中
		addEvent(this.elements[i],'mousedown',function(e){
			e.preventDefault();
			addEvent(document,'mousemove',function(e){
				e.preventDefault();
			});
		});
		*/
		//使滚动条置于顶部，防止页面下拖
		//addEvent(window,'scroll',scrollTop);
		
		//禁用选定文本,防止页面被拖动
		addEvent(this.elements[i],'mousedown',preDef);
		addEvent(this.elements[i],'mouseup',preDef);
		addEvent(this.elements[i],'selectstart',preDef);	//IE
	}
	return this;
}
Base.prototype.unlock=function(){
	for(var i=0;i<this.elements.length;i++){
		this.elements[i].style.display="none";
		parseFloat(sys.firefox)<4?document.body.style.overflow='auto':document.documentElement.style.overflow='auto';
		//removeEvent(window,'scroll',scrollTop);
		
		//解除禁用选定文本
		removeEvent(document,'mousedown',preDef);
		removeEvent(document,'mouseup',preDef);
		removeEvent(document,'selectstart',preDef);	
	}
	return this;
}

//设置动画
Base.prototype.animate=function(obj){

	for(var i=0;i<this.elements.length;i++){
		var element=this.elements[i];
		var attr=obj['attr']=='x'?'left':obj['attr']=='y'?'top':				//可选，x，y表示移动方向，默认左右移(未传值时)
					obj['attr']=='w'?'width':obj['attr']=='h'?'height':			//		w为width,h为height
					obj['attr']=='o'?'opacity':									//		o为透明度
					obj['attr']!=undefined?obj['attr']:'left';					//		其它不常用属性直接打
		var mul=obj['mul'];
		var start=obj['start']!=undefined?obj['start']:							//可选，初始位置(最好有写)
					attr=='opacity'?parseFloat(getStyle(element,attr))*100:		//		opacity默认*100
					parseInt(getStyle(element,attr));							//		opacity保留小数，其他取整
		var t=obj['t']!=undefined?obj['t']:10;									//可选，默认50毫秒，动一下
		var step=obj['step']!=undefined?obj['step']:20;	 						//可选，默认每次动10px
		var target=obj['target'];										//****二选一***，目标位置
		var alter=obj['alter'];											//****二选一***，alter为增量
		
		var speed=obj['speed']!=undefined?obj['speed']:6;						//可选，缓冲速度，默认为6
		var type=obj['type']==0?'constant':obj['type']==1?'buffer':'buffer';	//可选，0为匀速，1为缓冲，默认缓冲
		
		//增量与目标位置选择，二者都有默认目标位置
		if(alter!=undefined&&target==undefined){
			target=alter+start;
		}else if(alter==undefined&&target==undefined&&mul==undefined){
			throw new Error('alter增量或target目标量必须传一个');
		}
		
		if(start>target)step=-step;		//向左，向上

		if(attr=='opacity'){
			element.style.opacity=start/100;					//W3C
			element.style.filter='alpha(opacity='+start+')';	//IE
		}else{
			//element.style[attr]=start+'px';	//初始位置,IE会报错		
		}
		
		if(mul==undefined){
			var mul={};
			mul[attr]=target;
		}
		
		
		
		clearInterval(element.timer);	//不可少,防止侧栏抽搐,防止多次触发速度翻倍,但会造成同步动画不能实现(传入mul解决)
		
		//与直接使用timer相比，element.timer可防止与其他节点公用一个timer,导致中断
		element.timer=setInterval(function(){
			
			//创建一个布尔值，了解多个动画是否执行完毕
			var flag=true;
			
			for(var i in mul){			//执行多个属性变化, (i为mul对象的属性值)
				attr=i=='x'?'left':i=='y'?'top':i=='w'?'width':i=='h'?'height':i=='o'?'opacity':i!=undefined?i:'left';	
				target=mul[i];		
				
				if(type=="buffer"){		//缓冲
					step=attr=='opacity'?(target-parseFloat(getStyle(element,attr))*100)/speed:
								(target-parseInt(getStyle(element,attr)))/speed;
					step=step>0?Math.ceil(step):Math.floor(step);	//整数用ceil入,负数用floor舍,防止最后step有很多下为0，一下子跳
				}
				
				if(attr=='opacity'){		//透明度单独处理,其他是left,top,height,width的处理
					if(step==0){
						setOpacity();
						
					}else if(step>0&&Math.abs(parseFloat(getStyle(element,attr))*100-target)<=step){	//abs()转为正，离目标距离小于step时
						setOpacity();
					}else if(step<0&&(parseFloat(getStyle(element,attr))*100-target)<=Math.abs(step)){
						setOpacity();
					}else{
						var temp=parseFloat(getStyle(element,attr))*100;	//当前透明度值
						element.style.opacity=parseInt(temp+step)/100;
						element.style.filter='alpha(opacity='+parseInt(temp+step)+')';
					}
					
					if(parseInt(target)!=parseInt(getStyle(element,attr)*100))flag=false;					
					
				}else {
					if(step==0){
						setTarget();
					}else if(step>0&&Math.abs(getStyle(element,attr)-target)<=step){	//abs()转为正，离目标距离小于step时
						setTarget();
					}else if(step<0&&(getStyle(element,attr)-target)<=Math.abs(step)){
						setTarget();
					}else{
						element.style[attr]=getStyle(element,attr)+step+'px';	
					}	

					if(parseInt(target)!=parseInt(getStyle(element,attr)))flag=false;
				}
				
					//document.getElementById('aaa').innerHTML+=attr+'--'+step+'<br/>';
				

			}
			
			//防止fn多次执行
				if(flag){
					clearInterval(element.timer);		//停止
					if(obj.fn)obj.fn();			//列队动画,一个动画结束,执行fn里的动画或其他操作
				}
		},t);
		function setTarget(){	//结束设置
			element.style[attr]=target+'px';	//目标位置
			
		}
		function setOpacity(){	//透明度结束设置
			element.style.opacity=parseInt(target)/100;		//W3C设为目标值
			element.style.filter='alpha(opacity='+parseInt(target)+')';	//IE
		}
	}
	return this;
}
//插件入口
Base.prototype.extend=function(name,fn){
	Base.prototype[name]=fn;
}
/*
//拖拽功能
Base.prototype.drag=function(){

}
*/



/***各种事件****************/

//设置事件发生器

Base.prototype.bind=function(event,fn){
	for(var i=0;i<this.elements.length;i++){
		addEvent(this.elements[i],event,fn);
	}
	return this;
}

//触发点击事件
Base.prototype.click=function(fn){			
	for(var i=0;i<this.elements.length;i++){
		//this.elements[i].onclick=fn;
		addEvent(this.elements[i],'click',fn);
	}
	return this;		
}
//触发鼠标移入移出事件
Base.prototype.hover=function(over,out){
	for(var i=0;i<this.elements.length;i++){
		addEvent(this.elements[i],'mouseover',over);
		addEvent(this.elements[i],'mouseout',out);
	}
	return this;
}
//触发浏览器视口变化事件  
Base.prototype.resize=function(fn){
	for(var i=0;i<this.elements.length;i++){
		var element=this.elements[i];
		addEvent(window,'resize',function(){
			fn();
			if(element.offsetLeft>getInner().width-element.offsetWidth+getScroll().left){	//在右下角显示
				element.style.left=getInner().width+getScroll().left-element.offsetWidth+'px';
			}
			
			if(element.offsetTop>getInner().height-element.offsetHeight+getScroll().top){	
				element.style.top=getInner().height+getScroll().top-element.offsetHeight+'px';
				/*if(element.offsetTop<=0+getScroll().top){
					element.style.top=0+getScroll().top+'px';
					alert('top');
				}*/
			}	
			//顶部超出视口时
			if(element.offsetLeft<=0+getScroll().left){
					element.style.left=0+getScroll().left+'px';
			}			
			if(element.offsetTop<=0+getScroll().top){
					element.style.top=0+getScroll().top+'px';
			}
		});
	}
	return this;
}







