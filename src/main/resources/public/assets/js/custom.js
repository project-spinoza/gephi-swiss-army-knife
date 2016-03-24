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
	$("#select-depth_neighbor").selectBoxIt();
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
            var me = ui.draggable.clone();
            ui.draggable.draggable("disable");
            me.appendTo(this)
                .addClass("filternewClass");
	        //Add remove icon
			$("#filter_querycontainer span:last-child.easytree-node").append("<span class='removebtn glyphicon glyphicon-remove'></span>");
			//onclick remove  
			$('.removebtn').on('click',function(){
	   		  $(this).parents('.easytree-node').remove();
	   		  ui.draggable.draggable("enable");

	 	 	});  		
        }

    });
   

     ///////Range slider for range///////////
     //Range slider for Attributes Range filter 
  	$('#range-slider-range').jRange({
        from: 0,
        to: 8,
        step: 1,
        format: '%s',
        width: 180,
        showLabels: true,
        isRange : true
    }); 
    $('#range-slider-range').jRange('setValue', '1,8');
    //Range slider for Edgetype weight 
	$('#range-slider-edge').jRange({
	    from: 1.0,
	    to: 31.0,
	    step: 0.1,
	    format: '%s',
	    width: 180,
	    showLabels: true,
	    isRange : true,
    }); 
    $('#range-slider-edge').jRange('setValue', '1.0,31.0');
    //Range slider for Degree Range Topology filter 
	$('#range-slider-degree').jRange({
	    from: 1,
	    to: 36,
	    step: 1,
	    format: '%s',
	    width: 180,
	    showLabels: true,
	    isRange : true,
    }); 
    $('#range-slider-degree').jRange('setValue', '1,36');
    //Range slider for IN Degree Range Topology filter 
	$('#range-slider-in-degree').jRange({
	    from: 1,
	    to: 32,
	    step: 1,
	    format: '%s',
	    width: 180,
	    showLabels: true,
	    isRange : true,
    }); 
    $('#range-slider-in-degree').jRange('setValue', '1,32');
    //Range slider for Mutual Degree Range Topology filter 
	$('#range-slider-mutual-degree').jRange({
	    from: 1,
	    to: 10,
	    step: 1,
	    format: '%s',
	    width: 180,
	    showLabels: true,
	    isRange : true,
    }); 
    $('#range-slider-mutual-degree').jRange('setValue', '1,10');
    //Range slider for Mutual Degree Range Topology filter 
	$('#range-slider-out-degree').jRange({
	    from: 1,
	    to: 10,
	    step: 1,
	    format: '%s',
	    width: 180,
	    showLabels: true,
	    isRange : true,
    }); 
    $('#range-slider-out-degree').jRange('setValue', '1,10');
  	  //////range js end/////////

    //Disable all links in filters
    $("#jstree_demo_div a").click(function(e){ e.preventDefault(); });

   	//$("#queries_panel").on('click', 'a', function(){
   		//$("#contentwo").css({"display":"none"});
	//});
	//$("#equal_mod_class").mousedown(function(ev){
      //if(ev.which == 3)
     // {
          //  alert("Right mouse button clicked on element with id myId");
     // }
  	//});
    //Parameter load content
    $("#queries_panel").on('click', 'a', function() {
    	
  	  //var content_id = $(this).attr('href');
      //$('#parameter_load').hide().html($(content_id).html()).show(500);
        if($(this).attr('href')=="#equal_mod_class"){
        	
            $("#equal_mod_class_cont").show();
            $('#range_mod_class_cont, #parameter_load, #mask_edge_operator_filter_cont, #equal_degree_class_cont, #edge_weight_edges_filter_cont, #ego_net_topology_filter_cont, #deg_range_topology_filter_cont, #in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#range_mod_class"){
			$("#range_mod_class_cont").show();
            $('#equal_mod_class_cont, #parameter_load, #mask_edge_operator_filter_cont,#equal_degree_class_cont, #edge_weight_edges_filter_cont, #ego_net_topology_filter_cont, #deg_range_topology_filter_cont, #in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        } 
        else if($(this).attr('href')=="#mask_edge_operator_filter"){
			$("#mask_edge_operator_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load, #range_mod_class_cont,#equal_degree_class_cont, #edge_weight_edges_filter_cont, #ego_net_topology_filter_cont, #deg_range_topology_filter_cont, #in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#equal_degree_class"){
			$("#equal_degree_class_cont").show();
            $('#equal_mod_class_cont, #parameter_load, #range_mod_class_cont,#mask_edge_operator_filter_cont, #edge_weight_edges_filter_cont, #ego_net_topology_filter_cont, #deg_range_topology_filter_cont, #in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#edge_weight_edges_filter"){
			$("#edge_weight_edges_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load, #range_mod_class_cont,#mask_edge_operator_filter_cont, #equal_degree_class_cont, #ego_net_topology_filter_cont, #deg_range_topology_filter_cont, #in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#deg_range_topology_filter"){
			$("#deg_range_topology_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load,#ego_net_topology_filter_cont, #range_mod_class_cont,#mask_edge_operator_filter_cont, #equal_degree_class_cont, #edge_weight_edges_filter_cont, #in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#ego_net_topology_filter"){
			$("#ego_net_topology_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load, #range_mod_class_cont,#mask_edge_operator_filter_cont, #equal_degree_class_cont,#edge_weight_edges_filter_cont, #deg_range_topology_filter_cont, #in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#in_deg_topology_filter"){
			$("#in_deg_topology_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load,#ego_net_topology_filter_cont, #range_mod_class_cont,#mask_edge_operator_filter_cont, #equal_degree_class_cont, #edge_weight_edges_filter_cont, #deg_range_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#kcore_topology_filter"){
			$("#kcore_topology_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load,#ego_net_topology_filter_cont, #range_mod_class_cont,#mask_edge_operator_filter_cont, #equal_degree_class_cont, #edge_weight_edges_filter_cont, #deg_range_topology_filter_cont,#in_deg_topology_filter_cont, #mutualdeg_range_topology_filter_cont , #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#mutualdeg_range_topology_filter"){
			$("#mutualdeg_range_topology_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load,#ego_net_topology_filter_cont, #range_mod_class_cont,#mask_edge_operator_filter_cont, #equal_degree_class_cont, #edge_weight_edges_filter_cont, #deg_range_topology_filter_cont,#in_deg_topology_filter_cont, #kcore_topology_filter_cont, #neighbrs_net_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#neighbrs_net_topology_filter"){
			$("#neighbrs_net_topology_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load,#ego_net_topology_filter_cont, #range_mod_class_cont,#mask_edge_operator_filter_cont, #equal_degree_class_cont,#edge_weight_edges_filter_cont, #deg_range_topology_filter_cont,#in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #out_degree_range_topology_filter_cont').hide();
        }
        else if($(this).attr('href')=="#out_degree_range_topology_filter"){
			$("#out_degree_range_topology_filter_cont").show();
            $('#equal_mod_class_cont, #parameter_load, #ego_net_topology_filter_cont, #range_mod_class_cont, #mask_edge_operator_filter_cont, #equal_degree_class_cont,#edge_weight_edges_filter_cont, #deg_range_topology_filter_cont, #in_deg_topology_filter_cont, #kcore_topology_filter_cont, #mutualdeg_range_topology_filter_cont, #neighbrs_net_topology_filter_cont').hide();
        }
        else{
              
				console.log("No value is selected");
            }
            
    });

});  // End of document ready function.