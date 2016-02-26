$( document ).ready(function() {

/*
* GLOBAL variables/settings
*/
var sigmaSettings = '{ "eventsEnabled": true, "doubleClickEnabled": false, "enableEdgeHovering": true, "singleHover": true, "edgeHoverColor" : "edge", "edgeHoverColor": "default", "defaultEdgeHoverColor": "#777", "edgeHoverSizeRatio": 10, "edgeColor": "default", "defaultHoverLabelBGColor": "#fff", "defaultEdgeColor": "rgb(205, 220, 213)", "minEdgeSize": 0.2, "labelThreshold": 3, "defaultLabelColor": "#000", "animationsTime": 1000, "borderSize": 2, "outerBorderSize": 3, "defaultNodeOuterBorderColor": "rgb(72,227,236)", "edgeHoverHighlightNodes": "circle", "sideMargin": 10, "edgeHoverExtremities": true, "scalingMode": "outside", "enableCamera": true }';
var Gsetting = JSON.parse(sigmaSettings);

requestAjax ("http://localhost:9090/ajax");


/*
* @retrun ajax response
* Usage: sends ajax request to given URL
*/

function requestAjax (ajaxURL) {

	/*<![CDATA[*/
   $.ajax({
      type: "get", url: ajaxURL, data:{},
      async : true, beforeSend: function(xhr) {},
      //on successfull ajax request
      success: function (graphData) {
        $("#graphLoaderImg").css("display", "none");
          nodesObject = JSON.parse(graphData);
          var nodesCount = nodesObject.nodes.nodes.length;
          if(nodesCount > 0){
              showGraph(nodesObject.nodes, document.getElementById('container'), Gsetting);
          } else {
            alert ('no graph found data found');
          }
      },
      //on error in ajax request
      error: function(a, b, c){
      	alert ('error loading graph..');
        return false;
      }
   });
  /*]]>*/

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



function waitUntilValChange(variable, value) {
    if(value === variable) {
        setTimeout(waitUntilValChange(variable, value), 50);
        return;
    }
}

});