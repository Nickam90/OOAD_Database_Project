<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="service.ErrorService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Log In</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css"></style>
</head>
<body>
	<div class="container">
		<div class="header">
			<h2 class="text-muted">Tournament Hosting</h2>
			<hr>
		</div>
		<div class="col-md-4 well" id="content">
			<form method="POST" action="Login">
				<h2 class="form-signin-heading">Please Login</h2>
				<label>Username</label> 
				<input type="text" class="form-control" value="s123673" placeholder="Username" autofocus="" name="username"> 
				<br>
				<label>Password</label> 
				<input type="password" class="form-control" value="s123673" placeholder="Password" name="password">
				<br>
				<button class="btn btn-lg btn-primary " type="submit">Login</button>
			</form>
			<br>
   			 <% ErrorService error = (ErrorService) request.getAttribute("error"); %>
			 <% if (error != null) out.println(error.getError()); %>
		</div>
	</div>
	
</body>
</html>