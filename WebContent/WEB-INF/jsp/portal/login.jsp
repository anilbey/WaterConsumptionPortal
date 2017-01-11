<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  


    <!-- Custom styles for this template -->
    <link href="http://getbootstrap.com/examples/signin/signin.css" rel="stylesheet">

  
  
</head>
<body>

<div class="container">

<form:form modelAttribute="user" class="form-signin">
		<h2 class="form-signin-heading">Water Consumption Portal</h2>
	 username:   <form:input path="username"  class="form-control"/>  		 		   <br/>
	 password:  <form:password  path="password"  class="form-control"/>  				   <br/>
	 <h5>${errorMessage}</h5>
	 <input class="btn btn-lg btn-primary btn-block" type="submit" name="login" value="Login"/>
	 <br>
	 <c:if test="${not empty error}">
	 <div class="alert alert-danger">
	  <Span>${error}</Span>
      </div>	
	</c:if>
	 
	</form:form>
	
	
	  
	
	</div>
</body>
</html>