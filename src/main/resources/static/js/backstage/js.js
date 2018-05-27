/*left 导航*/
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
/*top 导航*/
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})
/*login*/
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
}); 
/*多选*/
function selectAll(){
 var checklist = document.getElementsByName ("selected");
   if(document.getElementById("controlAll").checked)
   {
   for(var i=0;i<checklist.length;i++)
   {
      checklist[i].checked = 1;
   } 
 }else{
  for(var j=0;j<checklist.length;j++)
  {
     checklist[j].checked = 0;
  }
 }
}
/*test-one*/
$(window).load(function(){	
		$(".done").click(function(){
			var this_li_ind = $(this).parent().parent("li").index();
			if($('.payment-wizard li').hasClass("jump-here")){
				$(this).parent().parent("li").removeClass("active").addClass("completed");
				$(this).parent(".wizard-content").slideUp();
				$('.payment-wizard li.jump-here').removeClass("jump-here");
			}else{
				$(this).parent().parent("li").removeClass("active").addClass("completed");
				$(this).parent(".wizard-content").slideUp();
				$(this).parent().parent("li").next("li:not('.completed')").addClass('active').children('.wizard-content').slideDown();
			}
		});
		
		$('.payment-wizard li .wizard-heading').click(function(){
			if($(this).parent().hasClass('completed')){
				var this_li_ind = $(this).parent("li").index();		
				var li_ind = $('.payment-wizard li.active').index();
				if(this_li_ind < li_ind){
					$('.payment-wizard li.active').addClass("jump-here");
				}
				$(this).parent().addClass('active').removeClass('completed');
				$(this).siblings('.wizard-content').slideDown();
			}
		});
		$('.payment-wizard li .wizard-heading').click(function(){
			if($(this).parent().not('completed')){
				var this_li_ind = $(this).parent("li").index();		
				var li_ind = $('.payment-wizard li.active').index();
				if(this_li_ind < li_ind){
					$('.payment-wizard li.active').addClass("jump-here");
				}
				$(this).parent().addClass('active').removeClass('completed');
				$(this).siblings('.wizard-content').slideDown();
			}
		});
	})