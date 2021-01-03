<!DOCTYPE html>
<html lang="en">
<head>
  <title></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
  </script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>



<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
		 google.charts.load('current', {
		        'packages':['geochart'],
		        // Note: you will need to get a mapsApiKey for your project.
		        // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
		        'mapsApiKey': 'AIzaSyAl3YnXUi8Xh57_Zr8XzCQLHx6CIiz1n-k'
		      });
		
		  function getSummary () {
				$.ajax({
					
					type : 'GET',
					headers : {
						Accept : "application/json; charset=utf-8",
						"Content-Type" : "application/json; charset=utf-8"
					},
				      url : '${pageContext.request.contextPath}/v1/India/getsummary',
					success : function(result) {
						
						document.getElementById("active").innerHTML = result['active'];
						document.getElementById("cured").innerHTML = result['recovered'];
						document.getElementById("death").innerHTML = result['deaths'];
						document.getElementById("total").innerHTML = result['cases'];
						
					}
				});
				}
			
				 getSummary();
		  		$.ajax({
		  			
		  			type : 'GET',
		  			headers : {
		  				Accept : "application/json; charset=utf-8",
		  				"Content-Type" : "application/json; charset=utf-8"
		  			},
		  		      url : '${pageContext.request.contextPath}/v1/India/stateInfo',
		  			success : function(result) {
		  				google.charts.load('current', {
		  					'packages' : [ 'corechart' ]
		  				});
		  				google.charts.setOnLoadCallback(function() {
		  					drawRegionsMap(result);
		  					buttonClickFunction('Maharashtra');
		  					getNews('Maharashtra');
		  				});
		  			}
		  		  });
		  		
	
		
		
		// google.charts.setOnLoadCallback(drawRegionsMap(result));



		 
	      function drawRegionsMap(resultMap) {
	      var data = new google.visualization.DataTable();
			data.addColumn('string', 'State Code')
	        data.addColumn('string', 'State');
			data.addColumn('number', 'Confirmed');
			var dataArray = [];
		 	$.each(resultMap, function(i, obj) {
				dataArray.push([obj.stateCode, obj.name, obj.confirmed ]);
			}); 

			data.addRows(dataArray);
			

	        var options = {}
	          options['region'] = 'IN';
	          options['displayMode']= 'regions';
	          options['resolution']= 'provinces';
	          options['colorAxis'] = {colors: ['#FFA1A1', '#FF4141']};
	          options['backgroundColor']= '#81d4fa';
	          
	        var mapchart = new google.visualization.GeoChart(document.getElementById('india-map'));

	        google.visualization.events.addListener(mapchart, 'select', selectHandler);

			function selectHandler() {
			    var selectedItem = mapchart.getSelection()[0];
			    if (selectedItem) {
			      var value = data.getValue(selectedItem.row, 1);
			      buttonClickFunction(value);
			      getNews(value);
			    }
			  }
	        
			mapchart.draw(data, options);
	      }

		window.buttonClickFunction = function (e) {
		$.ajax({
			
			type : 'GET',
			headers : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
		      url : '${pageContext.request.contextPath}/v1/India/stateInfo/'+e,
			success : function(result) {
				google.charts.load('current', {
					'packages' : [ 'corechart' ]
				});
				google.charts.setOnLoadCallback(function() {
					drawChart(result,e);
				});
			}
		});
		}
		function drawChart(result,e) {

			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Name');
			data.addColumn('number', 'Confirmed');
			var dataArray = [];
			$.each(result, function(i, obj) {
				dataArray.push([ obj.name, obj.confirmed ]);
			});

			data.addRows(dataArray);

			  var piechart_options = {
				is3D : true,
				slices : {}
			};
			var piechart = new google.visualization.PieChart(document
					.getElementById('state-pie'));
			piechart.draw(data, piechart_options);
			google.load("visualization", "1", {packages:["corechart"]});

			google.visualization.events.addListener(piechart, 'select', selectHandler);

			function selectHandler() {
			    var selectedItem = piechart.getSelection()[0];
			    if (selectedItem) {
			      var value = data.getValue(selectedItem.row, 0);
			      getNews(value);
			    }
			  }  
			document.getElementById("stateName-pie").innerHTML =   e;
		}


		window.getNews = function (e) {
			var nHTML = '<table class="table" >';
			$.ajax({
				
				type : 'GET',
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
			      url : '${pageContext.request.contextPath}/v1/India/GetNews/'+e,
				success : function(result) {
					
					jQuery.each(result, function(index, item) {
						nHTML += '<tr><td>' + '<a href=' + item.url +'>' + item.title + '</a></td></tr>';
			        });
			        nHTML += '</table>';
					document.getElementById("news-table").innerHTML =   nHTML;
					document.getElementById("news-RegionName").innerHTML =   e;
				}
			});
			}

		


	});
	
</script>

  <style>
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 550px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
        
    /* On small screens, set height to 'auto' for the grid */
    @media screen and (max-width: 767px) {
      .row.content {height: auto;} 
    }
  </style>
</head>
<body style="background-color:#F4F4F4;">
<nav class="navbar navbar-inverse visible-xs">		
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#"></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Covid-19 Dashboard</a></li>
        <li><a href="#">Under const.</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container-fluid">



  <div class="row content">
    <div class="col-sm-3 sidenav hidden-xs">
      <h2></h2>
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#section1">Covid-19 Dashboard</a></li>
      </ul><br>
    </div>
    <br>
    
     
      
    <div class="col-sm-9">
      <div class="row" style=" padding:10px;">
        <div class="col-sm-3" style="padding:inherit;">
       <div class="card" style="text-align:right;padding:inherit">
		    <div class="card-body">
		      <h3 class="card-title">Active</h3>
		       <hr>
		      <h4><p class="card-text" id="active"></p></h4>
		     
		    </div>
		  </div>
        </div>
        
        <div class="col-sm-3" style="padding:inherit;">
         <div class="card" style="text-align:right;padding:inherit">
		    <div class="card-body">
		      <h3 class="card-title">Death</h3>
		      <hr>
		      <h4><p class="card-text" id="death"></p></h4>
		    </div>
		  </div>
        </div>
        
      <div class="col-sm-3" style="padding:inherit;">
      	<div class="card" style="text-align:right;padding:inherit">
		    <div class="card-body">
		      <h3 class="card-title">Cured</h3>
		      <hr>
		      <h4><p class="card-text" id="cured"></p></h4>
		      
		    </div>
		  </div>
	</div>
        
        <div class="col-sm-3" style="padding:inherit;">
         <div class="card" style="text-align:right;padding:inherit">
		    <div class="card-body">
		      <h3 class="card-title">Total Cases</h3>
		       <hr>
		      <h4><p class="card-text" id="total"></p></h4>
		     
		    </div>
		  </div>
        </div>
        
      </div>
      <div class="row" style=" padding:10px;">
       
        <div class="col-sm-4" style="padding:inherit;">
          <div class="card" style="text-align:right;height:400px;padding:inherit;">
              <h4 class="card-title">India total cases</h4>
              <h6 class="card-subtitle mb-2 text-muted">click on state to get cases across districts : </h6>
              <hr>
		    <div class="card-body" id ="india-map">
		      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
		    </div>
		  </div>
        </div>
        
        <div class="col-sm-4" style="padding:inherit;">
           <div class="card" style="text-align:right;height:400px;padding:inherit;">
           <h4 class="card-title" id="stateName-pie"></h4>
           <h6 class="card-subtitle mb-2 text-muted">click on a district to get confirmed cases and news :  </h6>
            <hr>
		    <div class="card-body" id ="state-pie">
		      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
		    </div>
		  </div>
        </div>
        
         
        
      
      </div>
      <div class="row">
        <div class="col-sm-8" >
          <div class="card" style="height:400px;padding:10px">
          <h4 class="card-title" id="news-RegionName" style="text-align:right;"></h4>
           <h6 class="card-subtitle mb-2 text-muted" style="text-align:right;">news : click to read</h6>
            <hr>
		    <div class="card-body" id ="news-table" style="height:400px; overflow:auto">
		    </div>
		    <div style="text-align:center;padding:10px"> <p class="card-text"><small class="text-muted" >1 day ago</small></p></div>
		  </div>
        </div>
       
      </div>
    </div>
  </div>
</div>

</body>
</html>
