$(function(){
	//时间处理，去掉秒后面的
	for(var i=0;i<$("#index .content em").length();i++){
		var time=$("#index .content em").eq(i).html();
		var end=time.lastIndexOf(".");
		time=time.substring(0,end);
		$("#index .content em").eq(i).html(time);
	}
});