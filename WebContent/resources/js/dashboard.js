


google.charts.load('current', {
	'packages' : [ 'corechart' ]
});
google.charts.setOnLoadCallback(drawVisualization);
var view;
var viewOptions = 0;



function drawHourlyVisualization(hourlyDataInJson) {

	
	var data = new google.visualization.DataTable(hourlyDataInJson); 

	var options = {
		title : 'Hourly Water Consumption',
		vAxis : {
			title : 'Amount [meters cube]'
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

function drawVisualization() {

	var data = new google.visualization.DataTable(dataInJson); 

	var options = {
		title : 'Water Consumption',
		vAxis : {
			title : 'Amount [meters cube]'
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

	view = new google.visualization.DataView(data);
	if (viewOptions == 0) //to adjust the visibility of columns
		view.setColumns([ 0, 1 ]);
	else if (viewOptions == 1)
		view.setColumns([ 0, 1, 3 ]);
	else if (viewOptions == 2)
		view.setColumns([ 0, 1, 2 ]);
	else if (viewOptions == 3)
		view.setColumns([ 0, 1, 2, 3 ]);
	var chart = new google.visualization.ComboChart(document
			.getElementById('chart_div'));

	// The select handler. Call the chart's getSelection() method
	function selectHandler() {
		var selectedItem = chart.getSelection()[0];
		if (selectedItem) {
			var dateValue = data.getValue(selectedItem.row, 0);
			var amountValue = data.getValue(selectedItem.row, 1);
			
			
			if (amountValue > 0.0)
			{
				if ($("#viewRange").val() == "Day") {
			
					$.get("hourly-view?date=" + dateValue, function(data,
									status) {
								if (status == "success")
									
								{
									$("#hourly_chart_div").dialog({width: 800,height:600});
									drawHourlyVisualization(data.hourlyConsumption);
								}
								else
								{
									alert("non successful call");
								}
							})

				}
			}
			else
			{
				alert("no hourly data are available");	
			}
		}
	}
	google.visualization.events.addListener(chart, 'select', selectHandler);
	chart.draw(view, options);
}
$(document).ready(
	function() {
		
		$("#firstDate, #lastDate, #viewRange").change(
		function() {
			
			function parseDate(input) {
				  var parts = input.split('/');
				  return new Date(parts[2], parts[1]-1, parts[0]); // Note: months are 0-based
				}

			var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
			var diffDays = Math.round((parseDate($("#lastDate").val()).getTime() - parseDate($("#firstDate").val()).getTime())/(oneDay));
		
			if(diffDays > 0 )
			{
				if (($("#viewRange").val() == "Day" && diffDays < 60) || ($("#viewRange").val() == "Month") || ($("#viewRange").val() == "Week"))
				{
					$("#spinner_loader").addClass("fa fa-circle-o-notch fa-spin");
					
					$.get("consumptions?firstDate="
							+ $("#firstDate").val() + "&lastDate="
							+ $("#lastDate").val() + "&viewRange="
							+ $("#viewRange").val(), function(data,
							status) {
						dataInJson = data.householdConsumption;
						$("#spinner_loader").removeClass();
						drawVisualization();
					})
				}
				else if ($("#viewRange").val() == "Day")
				{
					alert("Day range should not exceed 60 days");
				}
			}
			else
			{
				alert("The second date value should be coming after the first one");
			}
		});
		$("#firstDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#lastDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});

	});


function setLineVisibilities() {

	var xhttp;
	if (window.XMLHttpRequest) {
		// code for modern browsers
		xhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange = function() {
	};

	if (document.getElementById('avg').checked
			&& document.getElementById('navg').checked) {
		viewOptions = 3;
	} else if (document.getElementById('avg').checked) {
		viewOptions = 2;
	} else if (document.getElementById('navg').checked) {
		viewOptions = 1;
	} else
		viewOptions = 0;
	drawVisualization();

	//end
	xhttp.send();
}

