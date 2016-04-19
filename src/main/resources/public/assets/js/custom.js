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
	$("#savedb_id").selectBoxIt();
	$("#savehost_id").selectBoxIt();
	$("#selectdeg").selectBoxIt();
	$("#selectlayout-boxit").selectBoxIt();
	$("#select-labelsizeboxit").selectBoxIt();
	$("#select-depth_ego").selectBoxIt();
	$("#select-depth_neighbor").selectBoxIt();
    // Easy tree
	$('#networkoperations-folder').easytree();
	//Example 1
   
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
			$(".user-menu-container-right").animate({"right": "0px"},function(){
				$(".zoom-container").css("right","302px");
				
				});
			
			$(".user-menu-container-right").removeClass("closed");

		} 
		else {
				$(".user-menu-container-right").animate({"right": "-282px"},function(){
					$(".zoom-container").css("right","20px");
				});
				$(".user-menu-container-right").addClass("closed");		
		}
	});
	/********************color picker jquery start********************************/
var $left = $(".left-grad");
					$("#ex1").gradientPicker({
						change: function(points, styles) {
							for (i = 0; i < styles.length; ++i) {
								$left.css("background-image", styles[i]);
								console.log(styles[i]);
							}
						},
						fillDirection: "45deg",
						controlPoints: ["white 10%", "grey 50%", "red 100%"]
					});
$(".degree-selectm #selectdeg").change(function(){
	$(this).find("option:selected").each(function(){

		if($(this).attr("value")=="degree_1"){
					var $left = $(".left-grad");
					$("#ex1").gradientPicker({
						change: function(points, styles) {
							for (i = 0; i < styles.length; ++i) {
								$left.css("background-image", styles[i]);
								console.log(styles[i]);
							}
						},
						fillDirection: "45deg",
						controlPoints: ["white 10%", "grey 50%", "red 100%"]
					});
					
					$("#ex1").show();
						}
		else if($(this).attr("value")=="degree_2"){
			$("#ex1").hide();
		}
		
		else{
			
			console.log("No value is selected");
		}
	});
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

/**********************jquery for datasource popups*****************************************/
	$('.setting-text').click(function(e){
		e.preventDefault();
	});
    $("#selectdata").change(function(){
        $(this).find("option:selected").each(function(){
            if($(this).attr("value")=="config_pop_database"){
            	$(".setting-text").attr('data-popup-open','popup-config-database');
            	$('#search-form input[type="text"]').prop('disabled', false);
            	$('#search-form input[type="submit"]').removeAttr("disabled");
            	$(".setting-text" ).click( function( ){
            		$(".pop-custom-db").css("display","block");
            		$(".pop-custom-es").css("display","none");
            		$(".pop-custom-file").css("display","none");
            		$(".pop-custom-file-up").css("display","none");
            		//alert('teststs');
            	});

            }
            else if ($(this).attr("value")=="config_pop_elasticsearch") {
            	$('#search-form input[type="text"]').prop('disabled', false);
            	$('#search-form input[type="submit"]').removeAttr("disabled");
            	$(".setting-text").attr('data-popup-open','popup-config-es');
            	$(".setting-text" ).click( function( ){
            		$(".pop-custom-db").css("display","none");
            		$(".pop-custom-es").css("display","block");
            		$(".pop-custom-file").css("display","none");
            		$(".pop-custom-file-up").css("display","none");
            		//alert('teststs');
            	});
            }
             else if ($(this).attr("value")=="file_upload_data") {
            	$(".setting-text").attr('data-popup-open','popup-config-file-up');
            	$('#search-form input[type="text"]').prop('disabled', false);
            	$('#search-form input[type="submit"]').removeAttr("disabled");
            	$(".setting-text" ).click( function( ){
            		$(".pop-custom-file-up").css("display","block");
            		$(".pop-custom-file").css("display","none");
            		$(".pop-custom-db").css("display","none");
            		$(".pop-custom-es").css("display","none");  
            	});
            }
            else if ($(this).attr("value")=="config_file_upload") {
            	$('#search-form input[type="text"]').prop('disabled', true);
            	$('#search-form input[type="submit"]').attr("disabled", "disabled"); 
            	$(".setting-text").attr('data-popup-open','popup-config-file');
            	$(".setting-text" ).click( function( ){
            		$(".pop-custom-file").css("display","block");
            		$(".pop-custom-db").css("display","none");
            		$(".pop-custom-es").css("display","none");
            		$(".pop-custom-file-up").css("display","none");
            		  
            	});
            }
    	});
	});
	 /*********************** file upload popup start****************/
	$('#graphFileUploadForm input[type="submit"]').prop('disabled', true);
    $("#filer_input").filer({
        limit: 1,
        maxSize: null,
        extensions: null,
        changeInput: '<div class="jFiler-input-dragDrop"><div class="jFiler-input-inner"><div class="jFiler-input-icon"><i class="icon-jfi-cloud-up-o"></i></div><div class="jFiler-input-text"><h3>Drag&Drop files here</h3> <span style="display:inline-block; margin: 15px 0">or</span></div><a class="jFiler-input-choose-btn blue">Browse Files</a></div></div>',
        showThumbs: true,
        theme: "dragdropbox",
        templates: {
            box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
            item: '<li class="jFiler-item">\
                        <div class="jFiler-item-container">\
                            <div class="jFiler-item-inner">\
                                <div class="jFiler-item-thumb">\
                                    <div class="jFiler-item-status"></div>\
                                    <div class="jFiler-item-info">\
                                        <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                        <span class="jFiler-item-others">{{fi-size2}}</span>\
                                    </div>\
                                    {{fi-image}}\
                                </div>\
                                <div class="jFiler-item-assets jFiler-row">\
                                    <ul class="list-inline pull-left">\
                                        <li>{{fi-progressBar}}</li>\
                                    </ul>\
                                    <ul class="list-inline pull-right">\
                                        <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                    </ul>\
                                </div>\
                            </div>\
                        </div>\
                    </li>',
            itemAppend: '<li class="jFiler-item">\
                            <div class="jFiler-item-container">\
                                <div class="jFiler-item-inner">\
                                    <div class="jFiler-item-thumb">\
                                        <div class="jFiler-item-status"></div>\
                                        <div class="jFiler-item-info">\
                                            <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                            <span class="jFiler-item-others">{{fi-size2}}</span>\
                                        </div>\
                                        {{fi-image}}\
                                    </div>\
                                    <div class="jFiler-item-assets jFiler-row">\
                                        <ul class="list-inline pull-left">\
                                            <li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
                                        </ul>\
                                        <ul class="list-inline pull-right">\
                                            <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                        </ul>\
                                    </div>\
                                </div>\
                            </div>\
                        </li>',
            progressBar: '<div class="bar"></div>',
            itemAppendToEnd: false,
            removeConfirmation: true,
            _selectors: {
                list: '.jFiler-items-list',
                item: '.jFiler-item',
                progressBar: '.bar',
                remove: '.jFiler-item-trash-action'
            }
        },
        dragDrop: {
            dragEnter: null,
            dragLeave: null,
            drop: null,
        },
        uploadFile: {
            url: "/graphFileUpload",
            data: null,
            type: 'POST',
            enctype: 'multipart/form-data',
            beforeSend: function(){},
            success: function(data, el){
            	$('#graphFileUploadForm input[type="submit"]').prop('disabled', false);
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    $("<div class=\"jFiler-item-others text-success\"><i class=\"icon-jfi-check-circle\"></i> Success</div>").hide().appendTo(parent).fadeIn("slow");    
                });
            },
            error: function(el){
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    //$("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> Error</div>").hide().appendTo(parent).fadeIn("slow");    
                });
            },
            statusCode: null,
            onProgress: null,
            onComplete: null
        },
        files: null,
        addMore: false,
        clipBoardPaste: true,
        excludeName: null,
        beforeRender: null,
        afterRender: null,
        beforeShow: null,
        beforeSelect: null,
        onSelect: null,
        afterShow: null,
        onRemove: function(itemEl, file, id, listEl, boxEl, newInputEl, inputEl){
        	$('#graphFileUploadForm input[type="submit"]').prop('disabled', true);
            var file = file.name;
            $.get('/removeUpload', {file: file});
        },
        onEmpty: null,
        options: null,
        captions: {
            button: "Choose Files",
            feedback: "Choose files To Upload",
            feedback2: "files were chosen",
            drop: "Drop file here to Upload",
            removeConfirmation: "Are you sure you want to remove this file?",
            errors: {
                filesLimit: "Only {{fi-limit}} files are allowed to be uploaded.",
                filesType: "Only Images are allowed to be uploaded.",
                filesSize: "{{fi-name}} is too large! Please upload file up to {{fi-maxSize}} MB.",
                filesSizeAll: "Files you've choosed are too large! Please upload files up to {{fi-maxSize}} MB."
            }
        }
    });

	 /***********************file upload popup end****************/
	 /*********************** Graph file upload popup start****************/
    $("#filer_input_graph").filer({
        limit: 1,
        maxSize: null,
        extensions: null,
        changeInput: '<div class="jFiler-input-dragDrop"><div class="jFiler-input-inner"><div class="jFiler-input-icon"><i class="icon-jfi-cloud-up-o"></i></div><div class="jFiler-input-text"><h3>Drag&Drop files here</h3> <span style="display:inline-block; margin: 15px 0">or</span></div><a class="jFiler-input-choose-btn blue">Browse Files</a></div></div>',
        showThumbs: true,
        theme: "dragdropbox",
        templates: {
            box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
            item: '<li class="jFiler-item">\
                        <div class="jFiler-item-container">\
                            <div class="jFiler-item-inner">\
                                <div class="jFiler-item-thumb">\
                                    <div class="jFiler-item-status"></div>\
                                    <div class="jFiler-item-info">\
                                        <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                        <span class="jFiler-item-others">{{fi-size2}}</span>\
                                    </div>\
                                    {{fi-image}}\
                                </div>\
                                <div class="jFiler-item-assets jFiler-row">\
                                    <ul class="list-inline pull-left">\
                                        <li>{{fi-progressBar}}</li>\
                                    </ul>\
                                    <ul class="list-inline pull-right">\
                                        <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                    </ul>\
                                </div>\
                            </div>\
                        </div>\
                    </li>',
            itemAppend: '<li class="jFiler-item">\
                            <div class="jFiler-item-container">\
                                <div class="jFiler-item-inner">\
                                    <div class="jFiler-item-thumb">\
                                        <div class="jFiler-item-status"></div>\
                                        <div class="jFiler-item-info">\
                                            <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                            <span class="jFiler-item-others">{{fi-size2}}</span>\
                                        </div>\
                                        {{fi-image}}\
                                    </div>\
                                    <div class="jFiler-item-assets jFiler-row">\
                                        <ul class="list-inline pull-left">\
                                            <li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
                                        </ul>\
                                        <ul class="list-inline pull-right">\
                                            <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                        </ul>\
                                    </div>\
                                </div>\
                            </div>\
                        </li>',
            progressBar: '<div class="bar"></div>',
            itemAppendToEnd: false,
            removeConfirmation: true,
            _selectors: {
                list: '.jFiler-items-list',
                item: '.jFiler-item',
                progressBar: '.bar',
                remove: '.jFiler-item-trash-action'
            }
        },
        dragDrop: {
            dragEnter: null,
            dragLeave: null,
            drop: null,
        },
        uploadFile: {
            url: "/fileUpload",
            data: null,
            type: 'POST',
            enctype: 'multipart/form-data',
            beforeSend: function(){},
            success: function(data, el){
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    $("<div class=\"jFiler-item-others text-success\"><i class=\"icon-jfi-check-circle\"></i> Success</div>").hide().appendTo(parent).fadeIn("slow");    
                });
            },
            error: function(el){
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    //$("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> Error</div>").hide().appendTo(parent).fadeIn("slow");    
                });
            },
            statusCode: null,
            onProgress: null,
            onComplete: null
        },
        files: null,
        addMore: false,
        clipBoardPaste: true,
        excludeName: null,
        beforeRender: null,
        afterRender: null,
        beforeShow: null,
        beforeSelect: null,
        onSelect: null,
        afterShow: null,
        onRemove: function(itemEl, file, id, listEl, boxEl, newInputEl, inputEl){
            var file = file.name;
            $.get('/removeUpload', {file: file});
        },
        onEmpty: null,
        options: null,
        captions: {
            button: "Choose Files",
            feedback: "Choose files To Upload",
            feedback2: "files were chosen",
            drop: "Drop file here to Upload",
            removeConfirmation: "Are you sure you want to remove this file?",
            errors: {
                filesLimit: "Only {{fi-limit}} files are allowed to be uploaded.",
                filesType: "Only Images are allowed to be uploaded.",
                filesSize: "{{fi-name}} is too large! Please upload file up to {{fi-maxSize}} MB.",
                filesSizeAll: "Files you've choosed are too large! Please upload files up to {{fi-maxSize}} MB."
            }
        }
    });

	 /***********************file upload popup end****************/
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
			$("#filter_querycontainer span:last-child.easytree-node").append("<span class='removebtn'></span>");
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
              
				//console.log("No value is selected");
            }
            
    });
//jquery for full screen
$(".fullscreen_icon").click(function(){
	$("body").toggleClass("full-screen");
	if ($("body").hasClass("full-screen")) {
		$(".zoom-container").css({"top":"10px","right":"30px"});
	}
	else if (!$("body").hasClass("full-screen")){
		$(".zoom-container").css({"top":"155px","right":"302px"});
		$(".user-menu-container-right").animate({"right": "0px"},function(){
				$(".user-menu-container-right").removeClass("closed");
				$(".zoom-container").css("right","302px");	
				});
	}
});

});  // End of document ready function.
