<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="service.ErrorService" import="dto.TournamentDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	ErrorService error = (ErrorService) request.getAttribute("error");
%>

<% TournamentDTO tDTO = (TournamentDTO) request.getSession().getAttribute("Tournament"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Tournament</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="bracket/jquery.bracket.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bracket/jquery.bracket.min.css" />
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
	<div class="container">
		<div class="header">
			<ul class="nav nav-pills pull-right">
				<%@include file="/WEB-INF/user/menu.jsp"%>
			</ul>
			<h2 class="text-muted">Tournament Hosting</h2>
			<hr>
		</div>
	</div>
	<h1>View Tournament</h1>
	<br>
	
	<% out.print("<h3>" + tDTO.getTournamentName() + "</h3>"); %>

	<%
		Boolean participant = (Boolean) request.getAttribute("participant");
	%>
	<%
		out.print("Are you participant in this tournament? : " + request.getAttribute("participant"));
	%>

	
 	<% if (error != null) out.println(error.getError()); %>
		
	<br>
	
	<div class="col-md-6 list-group">
		<ul>
			<form action="ViewTournament" method="post">
			
			<% if (participant != null)
				out.print("<input class=\"btn btn-lg btn-primary\" type=\"submit\" name=\"leaveButton\" value=\"Leave\" />");
			else
				out.print("<input class=\"btn btn-lg btn-primary\" type=\"submit\" name=\"joinButton\" value=\"Join\" />");
			%>
			</form>
		</ul>
	</div>
	</div>
	<div id="bracket"></div>
	<br>

</body>
</html>