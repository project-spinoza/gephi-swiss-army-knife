$(document).ready(function(){

	function setEditableCol (col) {
		col.editor = {
		  type:'text',
		  options:{
		    required:true
		  }
		};
	}

	function hideChildLayouts () {
		$("#rotation-layout-wrap").css( "display", "none" );
		$("#cont-expn-layout-wrap").css( "display", "none" );
		$("#force-atlas-layout-wrap").css( "display", "none" );
	}

	$("#layouts-div").change(function () {
	    var val = $("#layouts-div select option:selected").text();
	    hideChildLayouts ();

	    switch (val) {
	    	case "Clockwise Rotation":
	    		$("#rotation-layout-wrap").css( "display", "block" );
	    	break;
	    	case "Contraction":
	    		$("#cont-expn-layout-wrap").css( "display", "block" );
	    	break;
	    	case "Counter-Clockwise Rotation":
	    		$("#rotation-layout-wrap").css( "display", "block" );
	    	break;
	    	case "Expansion":
	    		$("#cont-expn-layout-wrap").css( "display", "block" );
	    	break;
	    	case "Force Atlas":
	    		$("#force-atlas-layout-wrap").css( "display", "block" );
	    	break;
	    	default:
	    	break;
	    }
	});

	//onclick method for search box
	$('#header input.textbox-text.validatebox-text.textbox-prompt').click(function(){
	    $('#searchField').searchbox({
        	width:'300px'
    	});
	});

	// editing clockwise cell rotation layout
	$('.rotation-layout').datagrid({
		onClickCell: function(index,field,value){
			if (field == 'angle-val') {
				$('.rotation-layout').datagrid('beginEdit', 0);	
			}
		}
	});

	// editing contraction cell rotation layout
	$('.cont-expn-layout').datagrid({
		onClickCell: function(index,field,value){
			if (field == 'contr-expn-val') {
				$('.cont-expn-layout').datagrid('beginEdit', 0);	
			}
		}
	});

	var col = $('.rotation-layout').datagrid('getColumnOption', 'angle-val');
	setEditableCol (col);
	var col = $('.cont-expn-layout').datagrid('getColumnOption', 'contr-expn-val');
	setEditableCol (col);


	//should be at the end of script.
	hideChildLayouts ();
});