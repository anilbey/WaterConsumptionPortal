<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link href="http://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map View</title>
<style>
    #map_canvas {
    width: 1400px;
    height: 900px;
}
    </style>

</head>
<body>


    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Water Consumption Portal</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="consumption">Dashboard</a></li>
            <li><a href="#">Map View</a></li> 
            <li><a href="logout">Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="consumption">Dashboard</a></li>
            <li class="active"><a href="#">Map <span class="sr-only">(current)</span></a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">





    <div class="container" id="map_canvas"></div>	
    	<div class="container" id="dialog" style ="visibility: hidden " >
		<div id="popup" style="width: 900px; height: 500px;">
			<table class="table table-striped">
			    <tbody>
			      <tr>
			        <td><b>Address</b></td>
			        <td><p id="popup-address"></p></td>
			      </tr>
			      <tr>
			        <td><b>Consumption Type</b></td>
			        <td><p id="popup-consumption-type"></p></td>
			      </tr>
			      <tr>
			        <td><b>Total Consumption</b></td>
			        <td><p id="popup-total-consumption"></p></td>
			      </tr>
			      <tr>
			        <td><b>Monthly Average</b></td>
			        <td><p id="popup-monthly-average"></p></td>
			      </tr>
			      <tr>
			        <td><b>Weekly Average</b></td>
			        <td><p id="popup-weekly-average"></p></td>
			      </tr>
			      <tr>
			        <td><b>Daily Average</b></td>
			        <td><p id="popup-daily-average"></p></td>
			      </tr>
			      
			    </tbody>
			  </table>
		</div>
		</div>
		
		
		
      </div>
		
		
      </div>
    </div>
		
		
		
		
				
    <script>
    var addresses = ${addresses};
    function initMap(){
        var coordinateData = 33;
        var map;
        var elevator;
        var myOptions = {
            zoom: 1,
            center: new google.maps.LatLng(48, 7), //western european viewpoint, this point will be shown if there are no markers.
            mapTypeId: 'roadmap'
        };
        map = new google.maps.Map($('#map_canvas')[0], myOptions);

        var i = 0;
        
        
        var latlngbounds = new google.maps.LatLngBounds();
        
        
        for (var x = 0; x < addresses.length; x++) {       
            $.getJSON('http://maps.googleapis.com/maps/api/geocode/json?address='+addresses[x].address+'&sensor=false', null, function (data) {
                var p = data.results[0].geometry.location;
                var latlng = new google.maps.LatLng(p.lat, p.lng);
                latlngbounds.extend(latlng); //for fitbounds
                coordinateData = latlng;         
                drawMarkers(latlng, i++, map);
                map.fitBounds(latlngbounds); //fitting the marker boundaries
                });
        }
        
    }

    function drawMarkers(latlng, i, map)
    {
    	var marker;
    	

    	//var info = "Total Consumption: " + addresses[i].totalConsumption + "\nCurrent Monthly Average: " + addresses[i].currentMonthlyAverage + "\nCurrent Weekly Average: " + addresses[i].currentWeeklyAverage + "\nCurrent Daily Average: " + addresses[i].currentDailyAverage;
    	
        if(addresses[i].isCommon == "individual")
        {
        	var marker = new google.maps.Marker({
                position: latlng,
                map: map,
                icon: 'http://www.google.com/mapfiles/markerI.png'
            }); 
        }
        else
        {
        	var marker = new google.maps.Marker({
                position: latlng,
                map: map,
                icon : 'http://www.google.com/mapfiles/markerC.png'
                
                
            }); 
        }
        
        marker.addListener('click', function () {
        	document.getElementById('popup-address').innerHTML = addresses[i].address;
        	document.getElementById('popup-consumption-type').innerHTML = addresses[i].isCommon;
        	document.getElementById('popup-total-consumption').innerHTML = addresses[i].totalConsumption;
        	document.getElementById('popup-monthly-average').innerHTML = addresses[i].currentMonthlyAverage;
        	document.getElementById('popup-weekly-average').innerHTML = addresses[i].currentWeeklyAverage;
        	document.getElementById('popup-daily-average').innerHTML = addresses[i].currentDailyAverage;
        	
        	$("#popup").dialog({width: 900,height:500});
          });
        
        
        

    }
    
</script>


    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnauGJ331Tq8FU6PRczl7nxspm8tMWHIM&callback=initMap">
<!--     </script> -->
  </body>

</html>