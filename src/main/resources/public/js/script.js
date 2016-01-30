$(document).ready(function(){

var Gsetting1 = '{ "eventsEnabled": true, "doubleClickEnabled": false, "enableEdgeHovering": true, "singleHover": true, "edgeHoverColor" : "edge", "edgeHoverColor": "default", "defaultEdgeHoverColor": "#777", "edgeHoverSizeRatio": 10, "edgeColor": "default", "defaultHoverLabelBGColor": "#fff", "defaultEdgeColor": "rgb(205, 220, 213)", "minEdgeSize": 0.2, "labelThreshold": 3, "defaultLabelColor": "#000", "animationsTime": 1000, "borderSize": 2, "outerBorderSize": 3, "defaultNodeOuterBorderColor": "rgb(72,227,236)", "edgeHoverHighlightNodes": "circle", "sideMargin": 10, "edgeHoverExtremities": true, "scalingMode": "outside", "enableCamera": true }';

    var Gsetting = JSON.parse(Gsetting1);

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

   $("#color-picker").bind("change paste keyup", function() {

      $('#container').css ('background', $('#color-picker').val());

    });

	//onclick method for search box
	$('#header input.textbox-text.validatebox-text.textbox-prompt').click(function(){
	    $('#searchField').searchbox({
        	width:'300px'
    	});
	});

	// editing clockwise cell rotation layout
	// $('.rotation-layout').datagrid({
	// 	onClickCell: function(index,field,value){
	// 		if (field == 'angle-val') {
	// 			$('.rotation-layout').datagrid('beginEdit', 0);	
	// 		}
	// 	}
	// });

	// editing contraction cell rotation layout
	// $('.cont-expn-layout').datagrid({
	// 	onClickCell: function(index,field,value){
	// 		if (field == 'contr-expn-val') {
	// 			$('.cont-expn-layout').datagrid('beginEdit', 0);	
	// 		}
	// 	}
	// });

	// var col = $('.rotation-layout').datagrid('getColumnOption', 'angle-val');
	// setEditableCol (col);
	// var col = $('.cont-expn-layout').datagrid('getColumnOption', 'contr-expn-val');
	// setEditableCol (col);

	//should be at the end of script.
	hideChildLayouts ();


$('#layout-run-btn').click (function () {
  graphAjaxRequest (2);
});


graphAjaxRequest (1);


function graphAjaxRequest (id) {

  var val = $("#layouts-div select option:selected").text();  

  if (id == 1) {
    url_dyn = "http://localhost:"+9090+"/ajax"; 
  }else {
    url_dyn = "http://localhost:"+9090+"/layout?layout="+val; 
  }
   

  $.ajax({
    type: "get",
    url: url_dyn,
    data:{},
    async : true, beforeSend: function(xhr) {},
    //on successfull ajax request
    success: function (graphData) {
       nodesObject = JSON.parse(graphData);

       nodesCount = nodesObject.nodes.nodes.length;
       if(nodesObject.nodes.nodes.length > 0){
            showGraph(nodesObject.nodes, document.getElementById('container'), Gsetting);
         }

    },
    //on error in ajax request
    error: function(a, b, c){
      alert('error loading graph');
    }
  });
}

function showGraph(givenData, givenContainer, givenSettings){
    $('#container').find("canvas").remove();    
//    givenContainer.innerHTML = "";
    s = new sigma( {
            graph : givenData,
            renderer: {
              container:givenContainer,
              type: 'canvas'
            },
            settings:givenSettings
          });

     totalEdges = s.graph.edges();

   for (var i in totalEdges) {
 	   totalEdges[i].type = 'curve';
 	 }

   s.graph.nodes().forEach(function(n) {
      n.originalColor = n.color;
      n.originalLabel = n.label;
   });

   s.graph.edges().forEach(function(e) {
     e.originalColor = e.color;
   });

   s.bind('clickEdge rightClickEdge', function(e) {
     console.log(e);
   });

   s.bind('overNode', function(e){

      $("#nodeInfoTable").empty();
      //papulate table
      var attr = e.data.node.attributes;
      var row = "<tr><td>" + 'ID:' + "</td><td>" + e.data.node.id; + "</td></tr>";
      $('#nodeInfoTable').append(row);
      var row = "<tr><td>" + 'Label:' + "</td><td>" + e.data.node.label; + "</td></tr>";
      $('#nodeInfoTable').append(row);
      var row = "<tr><td>" + 'OriginalLabel:' + "</td><td>" +  e.data.node.originalLabel; + "</td></tr>";
      $('#nodeInfoTable').append(row);
      var row = "<tr><td>" + 'Color:' + "</td><td>" + e.data.node.color; + "</td></tr>";
      $('#nodeInfoTable').append(row);
      var row = "<tr><td>" + 'B/w Centrality:' + "</td><td>" + attr["Betweenness Centrality"]; + "</td></tr>";
      $('#nodeInfoTable').append(row);
      var row = "<tr><td>" + 'Closeness Centrality:' + "</td><td>" + attr["Closeness Centrality"]; + "</td></tr>";
      $('#nodeInfoTable').append(row);
      var row = "<tr><td>" + 'PageRank:' + "</td><td>" + attr["PageRank"]; + "</td></tr>";
      $('#nodeInfoTable').append(row);
      var row = "<tr><td>" + 'NeighborCount:' + "</td><td>" + attr["NeighborCount"]; + "</td></tr>";
      $('#nodeInfoTable').append(row);
      var row = "<tr><td>" + 'Eccentricity:' + "</td><td>" + attr["Eccentricity"]; + "</td></tr>";
      $('#nodeInfoTable').append(row);

      var nodeId = e.data.node.id;
      toKeep = s.graph.neighbors(nodeId);
      toKeep[nodeId] = e.data.node;

      s.graph.nodes().forEach(function(n) {

        if (toKeep[n.id]){
          n.color = n.originalColor;
          n.label = n.originalLabel;
        } else{
          n.color = 'blue';
          n.label = "";
        }
    });

    s.graph.edges().forEach(function(e) {
      if (toKeep[e.source] && toKeep[e.target])
        e.color ='green';
      else
       e.color = e.originalColor;
    });
    s.refresh();
  });

  s.refresh();
   }

});