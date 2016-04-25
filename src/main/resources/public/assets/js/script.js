$( document ).ready(function() {

sigma.classes.graph.addMethod('neighbors', function(nodeId) {
var k,
neighbors = {},
index = this.allNeighborsIndex[nodeId] || {};
for (k in index)
  neighbors[k] = this.nodesIndex[k];
  return neighbors;
});


/*
* GLOBAL variables/settings
*/
var sigmaSettings = '{ "mouseWheelEnabled": false, "eventsEnabled": true, "doubleClickEnabled": false, "enableEdgeHovering": true, "singleHover": true, "edgeHoverColor" : "edge", "edgeHoverColor": "default", "defaultEdgeHoverColor": "#777", "edgeHoverSizeRatio": 10, "edgeColor": "default", "defaultHoverLabelBGColor": "#fff", "defaultEdgeColor": "rgb(205, 220, 213)", "minEdgeSize": 0.2, "labelThreshold": 3, "defaultLabelColor": "#fff", "animationsTime": 1000, "borderSize": 2, "outerBorderSize": 3, "defaultNodeOuterBorderColor": "rgb(72,227,236)", "edgeHoverHighlightNodes": "circle", "sideMargin": 10, "edgeHoverExtremities": true, "scalingMode": "outside", "enableCamera": true }';
var Gsetting = JSON.parse(sigmaSettings);
var statistics_btn;
var isMysqlConnected = false;
var isMongoDbConnected = false;



/*
*
*Layouts Submit Operations
*/
$(".layout_form").submit(function (e) {
    e.preventDefault();
    $(".graphLoader-run").css('display','block');
    requestAjax ("/layout", $("#" + this.id).serialize(), graphJsonHandler);
    $(".graphLoader-run").css('display','none');
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
    requestAjax ("/statistics", $("#" + this.id).serialize(), graphStatisticsHandler);
});

/*
*
*Graph File Upload Options Operations
*/
$("#graphFileUploadForm").submit(function (e) {
  e.preventDefault();
  $("#graphLoader").css('display','block');
  requestAjax ("/extractGraph", {}, function(graphData) {
    graphJsonHandler(graphData);
    $(".popup-close").click();
    $("#graphLoader").css('display','none');
  });
});

/*
*
*Tweets File Upload Options Operations
*/
$("#textfileUploadForm").submit(function (e) {
  e.preventDefault();
  $(".popup-close").click();
});

/*
*
*Database mysql connecting form submit
*/
$('.dbLoader').css('visibility','hidden');
$("#mysqldbForm").submit(function (e) {
  $('.dbLoader').css('visibility','visible');
  e.preventDefault();
  requestAjax ("/connectDB", $("#" + this.id).serialize(), function (resp) {
    isMysqlConnected = resp == "true" ? true : false;
    if (isMysqlConnected) {
      if ($('#mysqlFormSubmit').val() == 'Connect') {
        $('#mysqldbaction').val('disconnect');
        $('#mysqlFormSubmit').val("Disconnect");  
      }else {
        $('#mysqldbaction').val('connect');
        $('#mysqlFormSubmit').val("Connect");
      }
    } else {
      alert("Database service is down.")
    }
    $('.dbLoader').css('visibility','hidden');
  });
});


/*
*
*Database mongodb connecting form submit
*/
$("#mongodbForm").submit(function (e) {
  $('.dbLoader').css('visibility','visible');
  e.preventDefault();
  requestAjax ("/connectDB", $("#" + this.id).serialize(), function (resp) {
    isMongoDbConnected = resp == "true" ? true : false;
    if (isMongoDbConnected) {
      if ($('#mongodbFormSubmit').val() == 'Connect') {
        $('#mongodbaction').val('disconnect');
        $('#mongodbFormSubmit').val("Disconnect");  
      }else {
        $('#mongodbaction').val('connect');
        $('#mongodbFormSubmit').val("Connect");
      }
    } else {
      alert("Database service is down.")
    }
    $('.dbLoader').css('visibility','hidden');
  });
});

/*
*
*Search Form Submit
*/
$("img#searchLoader").css('visibility','hidden');
$("#search-form").submit(function (e) {
  e.preventDefault();
  $("img#searchLoader").css('visibility','visible');
  var searchVal = $('#search-form input[type=text]').val();
  var datasource = $('#datasources select#selectdata').val();
  requestAjax ("/search","searchStr="+searchVal+"&datasource="+datasource+"", function(graphData) {
    graphJsonHandler(graphData);
    $("img#searchLoader").css('visibility','hidden');
  });
});
/*
*
*Load Test graph
*/

//requestAjax ("http://localhost:9090/ajax", {}, graphJsonHandler);

/*
* @retrun ajax response
* Usage: sends ajax request to given URL
*/
function requestAjax (ajaxURL, formData, callBackFun) {
  /*<![CDATA[*/
   $.ajax({
      type: "get", url: ajaxURL, data:formData,
      async : true, timeout: 20*1000,  beforeSend: function(xhr) {},
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
  var statistics_id = formData.split('=')[1]; //get hidden field ID

  $("#chartContainer").empty();
  var targeted_popup_class = $("#"+statistics_btn).attr('data-popup-open');
  $('[data-popup="' + targeted_popup_class + '"]').fadeIn(350);

  switch (statistics_id) {
    case "averageDegree":
      $('span#canvasTitle').text("Average Degree");
      $("#chartContainer").append('<h3 style="margin: 20px 0 10px 0; color:#bababa">Results:</h3>');
      averageDegree(statsData);
    break;

    case "averageWeightedDegree":
      $('span#canvasTitle').text("Average Weighted Degree");
      $("#chartContainer").append('<h3 style="margin: 20px 0 10px 0; color:#bababa">Results:</h3>');
      averageDegree(statsData);
    break;
    case "graphDensity":
      $('span#canvasTitle').text("Graph Density");
      $("#chartContainer").append('<h3 style="margin: 20px 0 10px 0; color:#bababa">Results:</h3>');
      graphDensity (statsData);
    break;
    case "Modularity":
      $('span#canvasTitle').text("Modularity");
      $("#chartContainer").append('<h3 style="margin: 20px 0 10px 0; color:#bababa">Parameters:</h3>');
      $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">Randomize: On</h6>');
      $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">Use edge weights: On</h6>');
      $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">Resolution: 1.0</h6>');
      modularityClass (statsData);
    break;
    case "pageRank":
      $('span#canvasTitle').text("PageRank");
      $("#chartContainer").append('<h3 style="margin: 20px 0 10px 0; color:#bababa">Parameters:</h3>');
      $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">Epsilon = 0.001</h6>');
      $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">Probability = 0.85</h6>');
      pageRank (statsData);
    break;

    case "connectedComponents":
      $('span#canvasTitle').text("Connected Components");
      $("#chartContainer").append('<h3 style="margin: 20px 0 10px 0; color:#bababa">Results:</h3>');
      connectedComponents (statsData);
    break;
    case "avgClusterCoeficients":
      $('span#canvasTitle').text("Avg. Cluster Coeficients");
      clustering (statsData);
    break;

    case "eigenVectorCentrality":
      $('span#canvasTitle').text("EigenVector Centrality Report");
      $("#chartContainer").append('<h3 style="margin: 20px 0 10px 0; color:#bababa">Results:</h3>');
      eigencentralityStatis (statsData);
    break;

    case "avgPathLength":
      $('span#canvasTitle').text("Graph Distance Report");
      avgPathLength (statsData);
    break;
    case "hits":
      $('span#canvasTitle').text("HITS Metric Report");
      hits (statsData);
    break;
  }
}

function hits (statsData) {

  var jsonParsed = $.parseJSON(statsData);

  //degree graph
  $("#chartContainer").append('<div id="hub_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.hub);
  canvasGraph("hub_graph_canvas", "Hub Distribution", graphPoint, "Score", "Count");

  //In-degree graph
  $("#chartContainer").append('<div id="authority_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.authority);
  canvasGraph("authority_graph_canvas", "Authority Distribution", graphPoint, "Score", "Count");

}

function avgPathLength (statsData) {

  var jsonParsed = $.parseJSON(statsData);

  //degree graph
  $("#chartContainer").append('<div id="betweenesscentrality_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.betweenesscentrality);
  canvasGraph("betweenesscentrality_graph_canvas", "Between Esscentrality_graph_canvas Distribution", graphPoint, "Value", "Count");

  //In-degree graph
  $("#chartContainer").append('<div id="closnesscentrality_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.closnesscentrality);
  canvasGraph("closnesscentrality_graph_canvas", "Closness Centrality Distribution", graphPoint, "Value", "Count");

  //Out-degree graph
  $("#chartContainer").append('<div id="eccentricity_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.eccentricity);
  canvasGraph("eccentricity_graph_canvas", "Eccentricity Distribution", graphPoint, "Value", "Count");
}


function clustering (statsData) {
  var jsonParsed = $.parseJSON(statsData);
  //eigencentrality graph 
  $("#chartContainer").append('<div id="clustering_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.clustering);
  canvasGraph("clustering_graph_canvas", "Clustering Coefficient Distribution", graphPoint, "Score", "Count");  
}

function eigencentralityStatis (statsData) {
  var jsonParsed = $.parseJSON(statsData);
  //eigencentrality graph 
  $("#chartContainer").append('<div id="eigencentrality_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.eigencentrality);
  canvasGraph("eigencentrality_graph_canvas", "EigenVector Centrality", graphPoint, "Score", "Count");  
}

function connectedComponents (statsData) {
  var jsonParsed = $.parseJSON(statsData);
  $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">Number of Weakly Connected Components: '+jsonParsed.weekly_connected_components+'</h6>');
  $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">Number of Stronlgy Connected Components: '+jsonParsed.strongly_connected_components+'</h6>');
  //modularity graph
  $("#chartContainer").append('<div id="connectedcomponents_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.connected_components);
  canvasGraph("connectedcomponents_graph_canvas", "Connected Components Report", graphPoint, "Value", "Count");  
}

function pageRank (statsData) {
  var jsonParsed = $.parseJSON(statsData);
  //pageRank graph
  $("#chartContainer").append('<div id="pagerank_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.pageranks);
  canvasGraph("pagerank_graph_canvas", "PageRank Distribution", graphPoint, "Value", "Count");  
}

function modularityClass (statsData) {
  var jsonParsed = $.parseJSON(statsData);
  //modularity graph
  $("#chartContainer").append('<div id="modularity_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.modularity_class);
  canvasGraph("modularity_graph_canvas", "Modularity", graphPoint, "Value", "Count");
}

function graphDensity (statsData) {
  var jsonParsed = $.parseJSON(statsData);
  $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">'+jsonParsed.density+'</h6>');
}


function averageDegree (statsData) {

  var jsonParsed = $.parseJSON(statsData);
  $("#chartContainer").append('<h6 style="margin: 0px 0 20px 0; color:#bababa">'+jsonParsed.avgdegree+'</h6>');

  //degree graph
  $("#chartContainer").append('<div id="degree_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.degree);
  canvasGraph("degree_graph_canvas", "Degree Distribution", graphPoint, "Value", "Count");

  //In-degree graph
  $("#chartContainer").append('<div id="indegree_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.indegree);
  canvasGraph("indegree_graph_canvas", "In-Degree Distribution", graphPoint, "Value", "Count");

  //Out-degree graph
  $("#chartContainer").append('<div id="outdegree_graph_canvas" style="height: 300px; min-width: 100% !important; margin: 20px 0 20px 0;"></div>');
  var graphPoint = getSortedGraphPoints(jsonParsed.outdegree);
  canvasGraph("outdegree_graph_canvas", "Out-Degree Distribution", graphPoint, "Value", "Count");
}


function getSortedGraphPoints (jsonParsedData) {
  var keysUnsorted = [];
  $.each(jsonParsedData, function(k, v) {
   keysUnsorted.push(parseFloat(k));
  });
  keysUnsorted.sort(function(a, b){return a-b});

  var graphPoint = [];
  for (i = 0 ; i < keysUnsorted.length; i++){
    $.each(jsonParsedData, function(k, v) {
      if (keysUnsorted[i] == parseFloat(k)) {
        graphPoint.push({ x: keysUnsorted[i], y: jsonParsedData[k], color: "#ff0000"});
      }
    });
  }
  return graphPoint;
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
  nodesObject = JSON.parse(graphData);
  var nodesCount = nodesObject.nodes.nodes.length;
  if(nodesCount > 0){
      showGraph(nodesObject.nodes, document.getElementById('container'), Gsetting);
  } else {
    alert ('no graph found data found');
  }
}

var s;
var cameraSigmaGraph;
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

   cameraSigmaGraph = s.camera;
   s.graph.nodes().forEach(function(n) {
      n.originalColor = n.color;
      n.originalLabel = n.label;
   });

   s.graph.edges().forEach(function(e) {
     e.originalColor = e.color;
   });

   s.bind('clickEdge rightClickEdge', function(e) {
   //  console.log(e);
   });

  //  s.bind('overNode', function(e){

  //     $("#nodeInfoTable").empty();
  //     //papulate table
  //     var attr = e.data.node.attributes;
  //     var row = "<tr><td>" + 'ID:' + "</td><td>" + e.data.node.id; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);
  //     var row = "<tr><td>" + 'Label:' + "</td><td>" + e.data.node.label; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);
  //     var row = "<tr><td>" + 'OriginalLabel:' + "</td><td>" +  e.data.node.originalLabel; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);
  //     var row = "<tr><td>" + 'Color:' + "</td><td>" + e.data.node.color; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);
  //     var row = "<tr><td>" + 'B/w Centrality:' + "</td><td>" + attr["Betweenness Centrality"]; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);
  //     var row = "<tr><td>" + 'Closeness Centrality:' + "</td><td>" + attr["Closeness Centrality"]; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);
  //     var row = "<tr><td>" + 'PageRank:' + "</td><td>" + attr["PageRank"]; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);
  //     var row = "<tr><td>" + 'NeighborCount:' + "</td><td>" + attr["NeighborCount"]; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);
  //     var row = "<tr><td>" + 'Eccentricity:' + "</td><td>" + attr["Eccentricity"]; + "</td></tr>";
  //     $('#nodeInfoTable').append(row);

  //     var nodeId = e.data.node.id;
  //     var toKeep = s.graph.neighbors(nodeId);
  //     toKeep[nodeId] = e.data.node;

  //     s.graph.nodes().forEach(function(n) {

  //       if (toKeep[n.id]){
  //         n.color = n.originalColor;
  //         n.label = n.originalLabel;
  //       } else{
  //         n.color = '#cccccc';
  //         n.label = "";
  //       }
  //   });

  //   s.graph.edges().forEach(function(e) {
  //     if (toKeep[e.source] && toKeep[e.target])
  //       e.color ='green';
  //     else
  //      e.color = e.originalColor;
  //   });
  //   s.refresh();
  // });

  s.refresh();
   }


function sigmaGraphZoom (zIO, zRatio) {
  if (zIO) {
    cameraSigmaGraph.goTo({
      ratio: zRatio
    });
  } else {
    cameraSigmaGraph.goTo({
      ratio: zRatio
    });
  }
}


function waitUntilValChange(variable, value) {
    if(value === variable) {
        setTimeout(waitUntilValChange(variable, value), 50);
        return;
    }
}



/*************************************************************
              VERTICAL SLIDER JS
*************************************************************/
var zoomValCurrent;
var zoomValPrevious = 3;

  $(function() {
    $( "#slider-vertical" ).slider({
      orientation: "vertical",
      range: "min",
      min: 1,
      max: 10,
      step: 1,
      value: 3,
      slide: function( event, ui ) {
          zoomValCurrent = ui.value;
          zoomCalculator ();
      }
    });
  });


function zoomCalculator () {

  if (zoomValCurrent > zoomValPrevious) {
    var zoomRatio = cameraSigmaGraph.ratio;
    for (var i=0; i < (zoomValCurrent - zoomValPrevious); i++) {
      zoomRatio = zoomRatio / cameraSigmaGraph.settings('zoomingRatio');
    }
    sigmaGraphZoom(true, zoomRatio);
  }else if (zoomValCurrent < zoomValPrevious) {
    var zoomRatio = cameraSigmaGraph.ratio;
    for (var i=0; i < (zoomValPrevious - zoomValCurrent); i++) {
      zoomRatio = zoomRatio * cameraSigmaGraph.settings('zoomingRatio');
    }
    sigmaGraphZoom(false, zoomRatio);
  }
  zoomValPrevious = zoomValCurrent;
}

$('#zoomin-btn').click (function (e){
  var sVal = parseInt($("#slider-vertical").slider("value"));
  if (sVal < 10) {
    $("#slider-vertical").slider("value", sVal+1)
    zoomValCurrent = sVal+1;
    zoomCalculator();
  }
});

$('#zoomout-btn').click (function (e){
  var sVal = parseInt($("#slider-vertical").slider("value"));
  if (sVal > 0) {
    $("#slider-vertical").slider("value", sVal-1)
    zoomValCurrent = sVal-1;
    zoomCalculator();
  }
});


 //Firefox
 $('#container').bind('DOMMouseScroll', function(e){
     if(e.originalEvent.detail > 0) {
         $('#zoomout-btn').click();
     }else {
         //scroll up
         $('#zoomin-btn').click();
     }
     return false;
 });

 //IE, Opera, Safari
 $('#container').bind('mousewheel', function(e){
     if(e.originalEvent.deltaY > 0) {
         //scroll down
         $('#zoomout-btn').click();
     }else {
         //scroll up
         $('#zoomin-btn').click();
     }
     return false;
 });

});