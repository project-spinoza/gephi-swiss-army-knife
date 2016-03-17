// Window load function .
(function($){
	$(window).load(function(){
		$.mCustomScrollbar.defaults.scrollButtons.enable=true; //enable scrolling buttons by default
		$.mCustomScrollbar.defaults.axis="yx"; //enable 2 axis scrollbars by default						
		$("#jstree_demo_div").mCustomScrollbar();// Network operations.
		//$("#parameters_panel").mCustomScrollbar();// Parameters_panel
		$("#layout-contents").mCustomScrollbar();// Layout contents
		//$("#network_overview_panel").mCustomScrollbar();// Network Overview
		//$("#dynamic_panel").mCustomScrollbar();// Network Overview
		$(".popup-in").mCustomScrollbar();//  popup-box statistics 
	    //$("#queries_panel").mCustomScrollbar();//queries panel
	    $("#selectlayout-boxitSelectBoxItOptions").mCustomScrollbar();
	});
})(jQuery);	
// End of Window load function .

// A $( document ).ready() block.
$( document ).ready(function() {
	
					$( '.tree li' ).each( function() {
						if( $( this ).children( 'ul' ).length > 0 ) {
								$( this ).addClass( 'parent' );     
						}
				});
				
				$( '.tree li.parent > a' ).click( function( ) {
						$( this ).parent().toggleClass( 'active' );
						$( this ).parent().children( 'ul' ).slideToggle( 'fast' );
				});
	//select box 
	$("#selectdata").selectBoxIt();
	$("#selectdeg").selectBoxIt();
	$("#selectlayout-boxit").selectBoxIt();
	$("#select-labelsizeboxit").selectBoxIt();
	$("#select-depth_ego").selectBoxIt();
    // Easy tree
	$('#networkoperations-folder').easytree();
	
	// Nicescroll to whole page
	$("html").niceScroll({
		touchbehavior:false,
		horizrailenabled:false,
		cursorcolor:"#bababa",
		zindex:9000,
		cursoropacitymax:1,
		cursorwidth:7,
		background:"#1d1d1d",
		autohidemode:true,
		cursorborderradius:"0px",
		cursorborder:"0px",
		});
	// Decorating Checkbox
	$('#collapse3 input').iCheck({
		radioClass: 'iradio_square-grey',
		checkboxClass: 'icheckbox_square-grey',
		increaseArea: '20%' // optional
   }); // End of checkbox.
    console.log( "ready!" );
	$(".user-nav-slide").click(function () {
		if ($(".user-menu-container").hasClass("closed")) {
			$(".user-menu-container").animate({"left": "0px"});
			$(".user-menu-container").removeClass("closed");
		} else {
			$(".user-menu-container").animate({"left": "-282px"});
			$(".user-menu-container").addClass("closed");
		}
	});
		// Right sidebar
	$(".user-nav-slide-right").click(function () {
		if ($(".user-menu-container-right").hasClass("closed")) {
			$(".user-menu-container-right").animate({"right": "0px"});
			$(".user-menu-container-right").removeClass("closed");
		} else {
				$(".user-menu-container-right").animate({"right": "-282px"});
				$(".user-menu-container-right").addClass("closed");
		}
	});
 /*************************************layout dropdown*********************************************/
	$(".layout_panel select").change(function(){
        $(this).find("option:selected").each(function(){
            if($(this).attr("value")=="rotation"){
                $(".Rotate").show();
				$(".Scale").hide();
				$(".forceAtlas").hide();
				$(".fruchtermanReigngold").hide();
				$(".labelAdjust").hide();
				$(".noverLap").hide();
				$(".openOrd").hide();
				$(".randomLayout").hide();
				$(".yifanHu").hide();
				$(".yifanhuProportional").hide();
            }
            else if($(this).attr("value")=="scale"){
				$(".Scale").show();
				$(".Rotate").hide();
				$(".forceAtlas").hide();
				$(".fruchtermanReigngold").hide();
				$(".labelAdjust").hide();
				$(".noverLap").hide();
				$(".openOrd").hide();
				$(".randomLayout").hide();
				$(".yifanHu").hide();
				$(".yifanhuProportional").hide();
            }
			else if($(this).attr("value")=="forceatlas"){
				$(".forceAtlas").show();
				$(".Rotate").hide();
				$(".Scale").hide();
				$(".fruchtermanReigngold").hide();
				$(".labelAdjust").hide();
				$(".noverLap").hide();
				$(".openOrd").hide();
				$(".randomLayout").hide();
				$(".yifanHu").hide();
				$(".yifanhuProportional").hide();
            }
			else if($(this).attr("value")=="fruchtermanReigngold"){
				$(".fruchtermanReigngold").show();
				$(".forceAtlas").hide();
				$(".Rotate").hide();
				$(".Scale").hide();
				$(".labelAdjust").hide();
				$(".noverLap").hide();
				$(".openOrd").hide();
				$(".randomLayout").hide();
				$(".yifanHu").hide();
				$(".yifanhuProportional").hide();
            }
			else if($(this).attr("value")=="labelAdjust"){
				$(".labelAdjust").show();
				$(".fruchtermanReigngold").hide();
				$(".forceAtlas").hide();
				$(".Rotate").hide();
				$(".Scale").hide();
				$(".noverLap").hide();
				$(".openOrd").hide();
				$(".randomLayout").hide();
				$(".yifanHu").hide();
				$(".yifanhuProportional").hide();
            }
			else if($(this).attr("value")=="noverLap"){
				$(".noverLap").show();
				$(".labelAdjust").hide();
				$(".fruchtermanReigngold").hide();
				$(".forceAtlas").hide();
				$(".Rotate").hide();
				$(".Scale").hide();
				$(".openOrd").hide();
				$(".randomLayout").hide();
				$(".yifanHu").hide();
				$(".yifanhuProportional").hide();
            }
			else if($(this).attr("value")=="openOrd"){
				$(".openOrd").show();
				$(".noverLap").hide();
				$(".labelAdjust").hide();
				$(".fruchtermanReigngold").hide();
				$(".forceAtlas").hide();
				$(".Rotate").hide();
				$(".Scale").hide();
				$(".randomLayout").hide();
				$(".yifanHu").hide();
				$(".yifanhuProportional").hide();
            }
			else if($(this).attr("value")=="randomLayout"){
				$(".randomLayout").show();
				$(".openOrd").hide();
				$(".noverLap").hide();
				$(".labelAdjust").hide();
				$(".fruchtermanReigngold").hide();
				$(".forceAtlas").hide();
				$(".Rotate").hide();
				$(".Scale").hide();
				$(".yifanHu").hide();
				$(".yifanhuProportional").hide();
            }
			else if($(this).attr("value")=="yifanHu"){
				$(".yifanHu").show();
				$(".randomLayout").hide();
				$(".openOrd").hide();
				$(".noverLap").hide();
				$(".labelAdjust").hide();
				$(".fruchtermanReigngold").hide();
				$(".forceAtlas").hide();
				$(".Rotate").hide();
				$(".Scale").hide();
				$(".yifanhuProportional").hide();
            }
			else if($(this).attr("value")=="yifanhuProportional"){
				$(".yifanhuProportional").show();
				$(".yifanHu").hide();
				$(".randomLayout").hide();
				$(".openOrd").hide();
				$(".noverLap").hide();
				$(".labelAdjust").hide();
				$(".fruchtermanReigngold").hide();
				$(".forceAtlas").hide();
				$(".Rotate").hide();
				$(".Scale").hide();
            }
			
            else{
                //$(".box").hide();
				console.log("No value is selected");
            }
        });
    }).change();
	
	//Jquery for custom Lightbox NNN ////
			
    $('[data-popup-close]').on('click', function(e)  {
        var targeted_popup_class = jQuery(this).attr('data-popup-close');
        $('[data-popup="' + targeted_popup_class + '"]').fadeOut(350);
	        e.preventDefault();
	    });
    //Drag and Drop filter queries

    $(".filterdrag").draggable({
        helper: "clone",
        cursor: 'move',
        revert: 'invalid',
        opacity: "0.5"
    });
	//$("#queries_panel").mCustomScrollbar();//queries panel
	$("#queries_panel").mCustomScrollbar();//queries panel
    $("#filter_querycontainer").droppable({
        accept: $(".filterdrag"),
        hoverClass: "dropHover",
        drop: function (ev, ui) {
        	$(this).find(".replace_me").remove();
            var me = ui.draggable.clone()
            ui.draggable.draggable("disable")
            me.appendTo(this)
                .addClass("filternewClass");
        }

    });

     ///////Range slider for range///////////
  	$('.range-slider').jRange({
        from: 0,
        to: 8,
        step: 1,
        format: '%s',
        width: 180,
        showLabels: true,
        isRange : true
    });  
  	  ///////////////

    //Disable all links in filters
    $("#jstree_demo_div a").click(function(e){ e.preventDefault(); });

   	//$("#queries_panel").on('click', 'a', function(){
   		//$("#contentwo").css({"display":"none"});
	//});

    //Parameter load content
    $("#queries_panel").on('click', 'a', function() {
  	  //var content_id = $(this).attr('href');
      //$('#parameter_load').hide().html($(content_id).html()).show(500);
        if($(this).attr('href')=="#equal_mod_class"){
            $("#equal_mod_class_cont").show();
            $('#range_mod_class_cont, #parameter_load, #mask_edge_operator_filter_cont').hide();
        }
        else if($(this).attr('href')=="#range_mod_class"){
			$("#range_mod_class_cont").show();
            $('#equal_mod_class_cont, #parameter_load, #mask_edge_operator_filter_cont').hide();
        } 
        else if($(this).attr('href')=="#mask_edge_operator_filter"){
			$("#mask_edge_operator_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load, #range_mod_class_cont').hide();
        }
            
    });

});  // End of document ready function.