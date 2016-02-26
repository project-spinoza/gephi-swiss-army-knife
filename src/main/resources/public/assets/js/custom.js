// A $( document ).ready() block.
$( document ).ready(function() {
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