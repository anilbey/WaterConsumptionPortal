<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- 	for the custom checkboxes-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous">
	
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://www.google.com/jsapi"></script>
<!-- for the select menu -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>

<!-- 	for the dashboard theme -->
	    <!-- Custom styles for this template -->
    <link href="http://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet">


<script type="text/javascript">

var dataInJson = ${householdConsumption};

</script>

<script src="<c:url value="/resources/js/dashboard.js"/>"  type="text/javascript"></script>


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
            <li><a href="#">Dashboard</a></li>
            <li><a href="map">Map View</a></li> 
            <li><a href="logout">Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Dashboard <span class="sr-only">(current)</span></a></li>
            <li><a href="map">Map</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
         
			<h3 class="page-header">Welcome, ${user.username}</h3>

			<table>
			    <thead>
			    </thead>
			    <tbody>
			      <tr>
			        <td><h4 class="sub-header">Your consumption type is ${consumptionType}  :</h4></td>
			        <td>    
						<c:if test = "${consumptionType == 'individual'}">
							<img src="<c:url value="/resources/images/individual.png" />"/>
						</c:if>
						<c:if test = "${consumptionType == 'common'}">
							<img src="<c:url value="/resources/images/common.png" />"/>
						</c:if>
			        </td>
			      </tr>
			    </tbody>
		  </table>
			
			


	<form:form>
     <div class="well">
      <table class="table">
        <thead>
          <tr>
            <th>From:  <div class="input-group">
                <input id="firstDate" type="text" class="date-picker form-control" value="03/06/2015" readonly/>
                <label for="firstDate" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span>
                </label>
            </div></th>
            <th>To:  <div class="input-group">
                <input id="lastDate" type="text" class="date-picker form-control" value="14/10/2015" readonly/>
                <label for="lastDate" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span>
                </label>
            </div></th>
            <th>View Range: 	<div class="input-group">	<select id="viewRange" onchange="changeRange(this)" class="selectpicker">
			<option label="Days" value="Day">Days</option>
			<option label="Weeks" value="Week">Weeks</option>
			<option label="Months" value="Month" selected="selected">Months</option>
		</select></div> </th>
          </tr>
        </thead>
      </table>
          </div>  
 	<div class="checkbox"><label class="c-input c-checkbox"><input type="checkbox" id="avg" onclick="setLineVisibilities()"
		value="average"><span class="c-indicator"></span> Display your average consumption </label></div>
	<div class="checkbox"><label class="c-input c-checkbox"><input type="checkbox" id="navg" onclick="setLineVisibilities()"
		value="neighbourhoodAverage"><span class="c-indicator"></span> Display the average consumption
	in your neighbourhood</label></div>
	</form:form>
<div style="display: display-inline;">
<div style="font-size:240px; z-index:1000; width: 100%; position: absolute; z-index:1000; top:40%; left:40%">
	<i id="spinner_loader"  style="font-size:108px;"></i>
</div>
	<div class="container" id="chart_div" style="width: 1500px; height: 600px;"></div>
</div>
	 
	<div id="dialog" style ="visibility: hidden " >
		<div class="container" id="hourly_chart_div"></div>		
	</div>




      </div>
    </div>
</div>
</body>
</html>