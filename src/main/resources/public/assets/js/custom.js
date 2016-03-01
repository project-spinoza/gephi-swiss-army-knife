// Window load function .
(function($){
	$(window).load(function(){
		$.mCustomScrollbar.defaults.scrollButtons.enable=true; //enable scrolling buttons by default
		$.mCustomScrollbar.defaults.axis="yx"; //enable 2 axis scrollbars by default						
		$("#jstree_demo_div").mCustomScrollbar();// Network operations.
		$("#Queries_panel").mCustomScrollbar();// Queries_panel
		$("#parameters_panel").mCustomScrollbar();// Parameters_panel
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
	// Nicescroll to Network operations.
	/*$("#collapse_network").niceScroll({
		touchbehavior:false,
		cursorcolor:"#bababa",
		zindex:9000,
		cursoropacitymax:1,
		cursorwidth:7,
		background:"#434343",
		autohidemode:true,
		cursorborderradius:"0px",
		cursorborder:"0px",
		}); */
	// Dtree 
	//$(".tree").treemenu({delay:300}).openActive();
	// Nicescroll to whole page
	$("html").niceScroll({
		touchbehavior:false,
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
			$(".user-menu-container").animate({"margin-left": "0px"});
			$(".user-menu-container").removeClass("closed");
		} else {
			$(".user-menu-container").animate({"margin-left": "-282px"});
			$(".user-menu-container").addClass("closed");
		}
	});
		// Right sidebar
	$(".user-nav-slide-right").click(function () {
		if ($(".user-menu-container-right").hasClass("closed")) {
			$(".user-menu-container-right").animate({"margin-right": "0px"});
			$(".user-menu-container-right").removeClass("closed");
		} else {
				$(".user-menu-container-right").animate({"margin-right": "-282px"});
				$(".user-menu-container-right").addClass("closed");
		}
	});
 /*************************************layout dropdown*********************************************/
	$(".layout_panel select").change(function(){
        $(this).find("option:selected").each(function(){
            if($(this).attr("value")=="rotation"){
                //$(".box").not(".Rotate").hide();
                $(".Rotate").show();
				$(".Scale").hide();
            }
            else if($(this).attr("value")=="scale"){
                //$(".box").not(".Scale").hide();
                //$(".Scale").show();
				$(".Rotate").hide();
				$(".Scale").show();
            }
            else{
                //$(".box").hide();
				console.log("No value is selected");
            }
        });
    }).change();
	
	
});  // End of document ready function .