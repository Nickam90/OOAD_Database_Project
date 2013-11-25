<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="service.ErrorService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>View Tournament</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css"></style>
</head>
<body>
	<div class="container">
		<div class="header">
			<ul class="nav nav-pills pull-right">
			<%@include file="/WEB-INF/user/menu.jsp" %>
			</ul>
			<h2 class="text-muted">Tournament Hosting</h2>
			<hr>
		</div>
	</div>
	<h3>View Tournament</h3>
	
<div class="container">
	<div class="col-md-6 list-group">
		<ul>
		<form action="ViewTournament" method="post">
   		 <input class="btn btn-lg btn-primary" type="submit" name="joinButton" value="Join" />
   		 <input class="btn btn-lg btn-primary" type="submit" name="leaveButton" value="Leave" />
		</form>
		</ul>	
	</div>
	<br>
    <% ErrorService error = (ErrorService) request.getAttribute("error"); %>
	<% if (error != null) out.println(error.getError()); %>
</div>

</body>
</html>