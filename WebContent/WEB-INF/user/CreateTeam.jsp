<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="service.ErrorService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create Team</title>
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
	<h3>Create Team</h3>
	<div class="container">
		<div class="col-md-4 well">
        	<form method="POST" action="CreateTeam">
            	<h2 class="form-signin-heading">Please sign up</h2>
                <label> Team Name</label>
                <input type="text" class="form-control" name="team_name" placeholder="Team Name" autofocus="">
              	<br>
            	<label>Sport</label>
                <select class="form-control" name="Sport">
                <option value="" disabled selected>Please select a sport</option>
                <option value="soccer">Soccer</option>
                <option value="tennis">Tennis</option>
                <option value="pool">Pool</option>
                </select>        
              	<br>
                <button class="btn btn-lg btn-primary " type="submit">Create</button> 	
			</form>
			<br>
            <% ErrorService error = (ErrorService) request.getAttribute("error"); %>
			<% if (error != null) out.println(error.getError()); %>
		</div>
	</div>
	
</body>
</html>