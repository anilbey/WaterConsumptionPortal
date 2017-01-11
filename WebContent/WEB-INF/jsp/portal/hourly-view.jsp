<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HourlyView</title>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

	var dataInJson = ${hourlyConsumption};


	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawVisualization);
	var view;
	var viewOptions = 0;
	function drawHourlyVisualization() {

		
		var data = new google.visualization.DataTable(hourlyDataInJson); //JSON Constructor

		var options = {
			title : 'Title',
			vAxis : {
				title : 'Amount'
			},
			hAxis : {
				title : 'Time'
			},
			seriesType : 'bars',
			series : {
				1 : {
					type : 'line'
				},
				2 : {
					type : 'line'
				}
			},
			colors : [ '#23AAE2', '#e2431e', '#f1ca3a' ],
			interpolateNulls : false

		};


		var chart = new google.visualization.ComboChart(document
				.getElementById('hourly_chart_div'));

		chart.draw(data, options);

	}
</script>
</head>
<body>
<p>hourly view </p>

	<div id="hourly_chart_div" style="width: 1200px; height: 700px;"></div>

</body>
</html>