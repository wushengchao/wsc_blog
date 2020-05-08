
//封装ajax

//创建xhr
function createXHR(){
	if(typeof XMLHttpRequest!='undefined'){
		return new XMLHttpRequest();
	}else if(typeof ActiveXObject!='undefined'){
		var version=[
					'MSXML2XMLHttp.6.0',
					'MSXML2.XMLHttp.3.0',
					'MSXML2.XMLHttp'
		];
		for(var i=0;i<version.lenght;i++){
			try{
				return new ActiveXObject(version[i]);
			}catch(e){
				//跳过
			}
		}
	}else{
			throw new Error('您的系统或浏览器不支持XHR');
	}
}

//名值对转换为字符串
function params(data){
	var arr=[];
	for(var i in data){
		arr.push(encodeURIComponent(i)+'='+encodeURIComponent(data[i]));
	}
	return arr.join('&');
} 

//ajax实现
function ajax(obj){
	var xhr=createXHR();
	obj.url=obj.url+'?rand='+Math.random();
	obj.data=params(obj.data);
	if(obj.method==='get')obj.url+=(obj.url.indexOf('?')==-1?'?':'&')+obj.data;
	//alert(obj.url);
	if(obj.async===true){	//异步
		xhr.onreadystatechange=function(){			//readyState改变时触发 
			if(xhr.readyState==4){
				callback();
			}
		};
	}

	xhr.open(obj.method,obj.url,obj.async);
	
	if(obj.method==='post'){
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send(obj.data);
	}else{
		xhr.send(null);
	}

	if(obj.async===false){	//同步
		callback();
	}
	
	function callback(){
		if((xhr.status>=200&&xhr.status<300)||xhr.status==304){
			obj.success(xhr.responseText);	//回调传递参数
		}else{
			alert("Request was unsuccessful:"+xhr.status);
		}
	}
}


//调用ajax
/*
addEvent(document,'click',function(){
	ajax({
		method:'post',
		url:'ajaxServlet',
		data:{
			'name':'lee',
			'age':100
		},
		success:function(text){
			alert(text);
		},
		async:true
		
	});
});*/

