// JavaScript Document
$(document).ready(function(){
	$("#sidebar_left .position_right ").click(function(){
	$("#sidebar_left .position_right_hide").css("visibility","visible");	
	$("#sidebar_left .position_right").css("visibility","hidden");
	$("#sidebar_left .panel-group").css("display","none");	
	$("#sidebar_left").css("background","#000");	
	
	if($("#sidebar_right").hasClass("showing")){
		  $("#collapse_sidebar_left").css("width","5%");	
	 	  $("#center_content").css("width","78%");	
		  $("#sidebar_left").removeClass( "showing" ).addClass( "hidding" );
		}else {
		  $("#collapse_sidebar_left").css("width","5%");	
	 	  $("#center_content").css("width","88%");	
		  $("#sidebar_left").removeClass( "showing" ).addClass( "hidding" );
			}	
	})
	
	$("#sidebar_left .position_right_hide ").click(function(){
	$("#sidebar_left .position_right_hide").css("visibility","hidden");	
	$("#sidebar_left .position_right").css("visibility","visible");	
   
    	
	
	$("#sidebar_left .panel-group").css("display","block");		
	$("#sidebar_left").css("background","#353535");	
	
	if($("#sidebar_right").hasClass("showing")){
		  $("#collapse_sidebar_left").css("width","16.5%");		
		  $("#center_content").css("width","66%");		
		  $("#sidebar_left").removeClass( "hidding" ).addClass( "showing" ); //hidding 
		}else {
		  $("#collapse_sidebar_left").css("width","16.5%");		
		  $("#center_content").css("width","78%");	
		  $("#sidebar_left").removeClass( "hidding" ).addClass( "showing" ); //hidding 
			}
	
	})
/************************************************************************************/	
	$("#sidebar_right .position_left ").click(function(){
	$("#sidebar_right .position_left_hide").css("visibility","visible");	
	$("#sidebar_right .position_left").css("visibility","hidden");	
   	
	
	$("#sidebar_right .panel-group").css("display","none");	
	$("#sidebar_right button").css("display","none");	
	$("#sidebar_right").css("background","#000");	
	
	if($("#sidebar_left").hasClass("showing")){
		  $("#collapse_sidebar_right").css("width","5%");	
	 	  $("#center_content").css("width","78%");	
		  $("#sidebar_right").removeClass( "showing" ).addClass( "hidding" );
		}else {
		  $("#collapse_sidebar_right").css("width","5%");	
	 	  $("#center_content").css("width","88%");	
		  $("#sidebar_right").removeClass( "showing" ).addClass( "hidding" );
			}
	
	
	})
	
	$("#sidebar_right .position_left_hide ").click(function(){
	$("#sidebar_right .position_left_hide").css("visibility","hidden");	
	$("#sidebar_right .position_left").css("visibility","visible");	
    $("#sidebar_right").css("width","100%");
	
	$("#sidebar_right .panel-group").css("display","block");
	$("#sidebar_right button").css("display","block");	
	
	$("#sidebar_right").css("background","#353535");
	
	if($("#sidebar_left").hasClass("showing")){
		  $("#collapse_sidebar_right").css("width","16.5%");	
	 	  $("#center_content").css("width","66%");	
		  $("#sidebar_right").removeClass( "hidding" ).addClass( "showing" );//hidding
		}else {
		  $("#collapse_sidebar_right").css("width","16.5%");	
	 	  $("#center_content").css("width","78%");	
		  $("#sidebar_right").removeClass( "hidding" ).addClass( "showing" );
			}
	
	})
 /* $("#sidebar_left").on("hide.bs.collapse", function(){
    $(".btn").html('<span class="glyphicon glyphicon-collapse-down"></span> Open');
  });
  $("#demo").on("show.bs.collapse", function(){
    $(".btn").html('<span class="glyphicon glyphicon-collapse-up"></span> Close');
  });*/
});




(function($){
			$(window).load(function(){
				
				$.mCustomScrollbar.defaults.scrollButtons.enable=true; //enable scrolling buttons by default
				$.mCustomScrollbar.defaults.axis="yx"; //enable 2 axis scrollbars by default
				
				$("#content-l").mCustomScrollbar();	//jstree_demo_div			
				$("#jstree_demo_div").mCustomScrollbar();//Queries_panel
				$("#Queries_panel").mCustomScrollbar();//Queries_panel
				
				$(".all-themes-switch a").click(function(e){
					e.preventDefault();
					var $this=$(this),
						rel=$this.attr("rel"),
						el=$(".content");
					switch(rel){
						case "toggle-content":
							el.toggleClass("expanded-content");
							break;
					}
				});
				
			});
		})(jQuery);
		
		
		
		
$(function () {
    // 6 create an instance when the DOM is ready
    $('#jstree').jstree();
    // 7 bind to events triggered on the tree
    $('#jstree').on("changed.jstree", function (e, data) {
      console.log(data.selected);
    });
    // 8 interact with the tree - either way is OK
    $('#jstree button').on('click', function () {
      $('#jstree').jstree(true).select_node('child_node_1');
      $('#jstree').jstree('select_node', 'child_node_1');
      $.jstree.reference('#jstree').select_node('child_node_1');
	  
	  $('#jstree').jstree(true).select_node('child_node_2');
      $('#jstree').jstree('select_node', 'child_node_2');
      $.jstree.reference('#jstree').select_node('child_node_2');
	  
	  $('#jstree').jstree(true).select_node('child_node_3');
      $('#jstree').jstree('select_node', 'child_node_3');
      $.jstree.reference('#jstree').select_node('child_node_3');
    });
  });