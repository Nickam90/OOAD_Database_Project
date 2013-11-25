<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="service.ErrorService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sign Up</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css"></style>
</head>
<body>
	<div class="container">
    	<div class="header">
        	<ul class="nav nav-pills pull-right">
            	<li><a href="LogIn.jsp">Log in</a></li>
            	<li><a href="SignUp.jsp">Sign up</a></li>
            </ul>
            <h2 class="text-muted">Tournament Hosting</h2>
            <hr>
    	</div>
	</div>
    <div class="container">
    	<div class="col-md-4 well">
        	<form class="form-signin" role="form"  method="POST" action="Signup">
        		<h2 class="form-signin-heading">Please sign up</h2>
        		<label>Email address</label>
       			<input type="email" class="form-control" placeholder="Email address" autofocus="" name="email">
        		<label>Username</label>
        		<input type="text" class="form-control" placeholder="Username" name="username">
        		<label>Password</label>
        		<input type="password" class="form-control" placeholder="Password" name="password">
        		<br>
        		<button class="btn btn-lg btn-primary " type="submit">Submit</button>
        	</form>
        	<br>
    		<% ErrorService error = (ErrorService) request.getAttribute("error"); %>
			<% if (error != null) out.println(error.getError()); %>
        </div>
	</div>
</body>
</html>