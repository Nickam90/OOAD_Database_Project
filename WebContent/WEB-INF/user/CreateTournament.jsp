<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create Tournament</title>
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
    	<div class="col-md-4 well">
        	<form class="form-signin" method="POST" action="CreateTeam">
            	<h2 class="form-signin-heading">Please sign up</h2>
                <label> Tournament Name</label>
                <input type="text" class="form-control" name="tournament_name" placeholder="Tournament Name" autofocus="">
                <br>
                <label>Sport</label>
                	<select class="form-control" name="Sport">
                    	<option value="" disabled selected>Please select a sport</option>
                        <option value="sport1">Soccer</option>
                        <option value="sport2">Tennis</option>
                        <option value="sport3">Pool</option>
                    </select>
                <br>
                <label>Tournament Format</label>
                	<select class="form-control" name="Tournament Format">
                    	<option value="" disabled selected>Please select a format</option>
                        <option value="format1">Single Elimination</option>
                        <option value="format2">Double Elimination</option>
                    </select>
                <br>
                <label>Tournament Type</label>
                	<select class="form-control" name="Tournament Type">
                    	<option value="" disabled selected>Please select a type</option>
                        <option value="type1">Open</option>
                        <option value="type2">Invitation</option>
                    </select>
                <br>
                <label>Participant Count</label>
                	<input type="text" class="form-control" name="size" placeholder="Participant Count">
                <br>
                <label>Starting Date</label>
                	<input type="text" class="form-control" name="Starting Date" placeholder="Starting Date">
                <br>
                <label>Info</label>
                	<input type="text" class="form-control" name="info" placeholder="Info">
                <br>
                	<button class="btn btn-lg btn-primary " type="submit">Create</button>    	
        	</form>
        </div>
	</div>
</body>
</html>