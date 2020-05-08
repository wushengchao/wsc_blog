$(function(){
	$(function(){
		$("aside .all").click(function(){
			$("aside .all").css('color','red');
			$("aside .ready").css('color','#2F6982');
			$("aside .wait").css('color','#2F6982');
			$("section .all-blog").show('slow');
			$("section .ready-blog").hide('slow');
			$("section .wait-blog").hide('slow');
		});
		$("aside .ready").click(function(){
			$("aside .all").css('color','#2F6982');
			$("aside .ready").css('color','red');
			$("aside .wait").css('color','#2F6982');
			$("section .ready-blog").show('slow');
			$("section .all-blog").hide('slow');
			$("section .wait-blog").hide('slow');
		});
		$("aside .wait").click(function(){
			$("aside .all").css('color','#2F6982');
			$("aside .ready").css('color','#2F6982');
			$("aside .wait").css('color','red');
			$("section .wait-blog").show('slow');
			$("section .all-blog").hide('slow');
			$("section .ready-blog").hide('slow');
			
		});
	});
	
});