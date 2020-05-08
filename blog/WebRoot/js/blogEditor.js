$(function(){
	var editor = $('#blog_content').wangEditor();
	$('#clear').click(function(e){
		editor.html('');
	});
	$('.wangEditor-textarea-container').css('background','#E2E2E2');
	$('.wangEditor-textarea').css('background','#E2E2E2').css('color','#666');
	
	$("form")[0].reset(); //刷新时重置

});