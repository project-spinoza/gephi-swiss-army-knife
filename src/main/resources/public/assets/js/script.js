$( document ).ready(function() {

/*
* GLOBAL variables/settings
*/
var sigmaSettings = '{ "eventsEnabled": true, "doubleClickEnabled": false, "enableEdgeHovering": true, "singleHover": true, "edgeHoverColor" : "edge", "edgeHoverColor": "default", "defaultEdgeHoverColor": "#777", "edgeHoverSizeRatio": 10, "edgeColor": "default", "defaultHoverLabelBGColor": "#fff", "defaultEdgeColor": "rgb(205, 220, 213)", "minEdgeSize": 0.2, "labelThreshold": 3, "defaultLabelColor": "#000", "animationsTime": 1000, "borderSize": 2, "outerBorderSize": 3, "defaultNodeOuterBorderColor": "rgb(72,227,236)", "edgeHoverHighlightNodes": "circle", "sideMargin": 10, "edgeHoverExtremities": true, "scalingMode": "outside", "enableCamera": true }';
var Gsetting = JSON.parse(sigmaSettings);
var statistics_btn;

/*
*
*Layouts Submit Operations
*/
$(".layout_form").submit(function (e) {
    e.preventDefault();
    requestAjax ("http://localhost:9090/layout", $("#" + this.id).serialize(), graphJsonHandler);
});

/*
*
*Statistics Submit Operations
*/
$(".statistics_form").submit(function (e) {
    e.preventDefault();
//var temp = $("#" + this.id).serialize();
 //  alert(temp);
    statistics_btn = $(this).find("input[type=submit]").attr('id');
    requestAjax ("http://localhost:9090/statistics", $("#" + this.id).serialize(), graphStatisticsHandler);
});


/*
*
*Load Test graph
*/
requestAjax ("http://localhost:9090/ajax", {}, graphJsonHandler);


/*
* @retrun ajax response
* Usage: sends ajax request to given URL
*/
function requestAjax (ajaxURL, formData, callBackFun) {
	/*<![CDATA[*/
   $.ajax({
      type: "get", url: ajaxURL, data:formData,
      async : true, beforeSend: function(xhr) {},
      //on successfull ajax request
      success: function (graphData) {
        callBackFun(graphData, formData);
      },
      //on error in ajax request
      error: function(a, b, c){
      	alert ('Error requested graph Operation.');
        return false;
      }
   });
  /*]]>*/
}




/*
* @retrun none
* Usage: Statistics callback
*/
function graphStatisticsHandler (statsData, formData){  
  var statistics_id = formData.split('=')[1];

  $("#chartContainer").empty();
  var targeted_popup_class = $("#"+statistics_btn).attr('data-popup-open');
  $('[data-popup="' + targeted_popup_class + '"]').fadeIn(350);

  switch (statistics_id) {
    case "averageDegree":
      averageDegree(statsData);
    break;
  }
}

function averageDegree (statsData) {

  var jsonParsed = $.parseJSON(statsData);

  $('span#canvasTitle').text("Average Degree Statistics");
  $("#chartContainer").append('<h3 style="margin: 20px 0 10px 0; color:#bababa">Results:</h3>');
  $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">Average Degree: '+jsonParsed.avgdegree+'</h6>');

  //degree graph
  $("#chartContainer").append('<div id="degree_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = [];
  $.each(jsonParsed.degree, function(k, v) {
   graphPoint.push({ x: parseFloat(v), y: parseFloat(k) });
  });
  canvasGraph("degree_graph_canvas", "Degree Distribution", graphPoint, "Count", "Value");

  //In-degree graph
  $("#chartContainer").append('<div id="indegree_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = [];
  $.each(jsonParsed.indegree, function(k, v) {
   graphPoint.push({ x: parseFloat(v), y: parseFloat(k) });
  });
  canvasGraph("indegree_graph_canvas", "In-Degree Distribution", graphPoint, "Count", "Value");

  //Out-degree graph
  $("#chartContainer").append('<div id="outdegree_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = [];
  $.each(jsonParsed.outdegree, function(k, v) {
   graphPoint.push({ x: parseFloat(v), y: parseFloat(k) });
  });
  canvasGraph("outdegree_graph_canvas", "Out-Degree Distribution", graphPoint, "Count", "Value");
}

function canvasGraph (container, gtitle, data, xLabel, yLabel){
  var options = {
    title: {
      text: gtitle+""
    },
    axisY:{ 
        title: yLabel+"",
    },
    axisX: {
        title: xLabel+"",
    },
    animationEnabled: true,
    data: [
    {
      type: "spline", //change it to line, area, column, pie, etc
      dataPoints: data
    }
    ]
  };
  $("#"+container).CanvasJSChart(options);
}

/*
* @retrun none
* Usage: Forward parsed graph data received from server to Showgraph
*/
function graphJsonHandler (graphData){
  $("#graphLoaderImg").css("display", "none");
  nodesObject = JSON.parse(graphData);
  var nodesCount = nodesObject.nodes.nodes.length;
  if(nodesCount > 0){
      showGraph(nodesObject.nodes, document.getElementById('container'), Gsetting);
  } else {
    alert ('no graph found data found');
  }
}

function showGraph(givenData, givenContainer, givenSettings){
    $('#container').find("canvas").remove();      
//    givenContainer.innerHTML = "";
    var s = new sigma( {
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
      var toKeep = s.graph.neighbors(nodeId);
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