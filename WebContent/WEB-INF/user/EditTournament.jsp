<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="service.ErrorService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% ErrorService error = (ErrorService) request.getAttribute("error"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Tournament</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="bracket/jquery.bracket.min.js"></script>
<link rel="stylesheet" type="text/css" href="bracket/jquery.bracket.min.css" />
<style type="text/css"></style>
<script type="text/javascript">
	var bracketData = $.parseJSON('${bracketData}');
	$(function() {
		$('#bracket').bracket({
			init : bracketData
		/* data to initialize the bracket with */})
	})
</script>
</head>
<body>
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
	<h3>Edit Tournament</h3>
	
   <%
			if (error != null) {
				out.print("<div class=\"col-md-4 well\" id=\"content\">");
				out.println(error.getError());
				out.print("</div>");
			}	
	%>
	
	<% out.print("Are you organisor of this tournament? : " + request.getAttribute("organisor"));%>
	
<div class="col-md-6 list-group">
		<ul>
		<form action="ViewTournament" method="post">
   		 <input class="btn btn-lg btn-primary" type="submit" name="StartButton" value="Start" />
   		 <input class="btn btn-lg btn-primary" type="submit" name="editButton" value="Edit" />
   		 <input class="btn btn-lg btn-primary" type="submit" name="resButton" value="Enter Results" />
   		 <input class="btn btn-lg btn-primary" type="submit" name="delButton" value="Delete" />
		</form>
		</ul>	
	</div>
	<div id="bracket">
	</div>
	<br>

</body>
		
</html>