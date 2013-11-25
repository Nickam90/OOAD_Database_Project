<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home</title>
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
	<div class="container">
		<h3>Hello ${userName}</h3>
	</div>
	<div class="container">
		<div class="col-md-6 list-group">
			<h2>Participant In</h2>
			<ul>
				 ${partInList}
			</ul>
		</div>
		<div class="col-md-6 list-group">
			<h2>Organizer of</h2>
			<ul>
				 ${orgOfList}
			</ul>
		</div>
	</div>
</body>
</html>