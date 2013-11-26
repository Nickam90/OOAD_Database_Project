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
		<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addModal">Add Member</button>
		<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#deleteModal">Delete Member</button>
		<button class="btn btn-warning btn-lg" data-toggle="modal" data-target="#editModal">Edit Team</button>
		<button class="btn btn-danger btn-lg" data-toggle="modal" data-target="#deleteModal">Delete Team</button>
		<button class="btn btn-primary btn-lg">Start/Finnish tournament</button>
	</div>
	<div id="bracket">
	</div>
	<!-- Modal -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h4 class="modal-title">Add Team</h4>
      			</div>
				<div class="modal-body">
					<form method="POST" action="TournamentManager" >
						<label>Team name</label>
						<input class="form-control" type="text" name="name">
						<br>
						<input type="hidden" name="action" value="add team">
						<input type="hidden" name="id" value="">
						<button type="submit" class="btn btn-lg btn-primary" >Add Team</button>
					</form>
				</div> 
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="deleteMemberModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h4 class="modal-title">Delete Team</h4>
      			</div>
				<div class="modal-body">
					<form method="POST" action="TournamentManager" >
						<label>Team name</label>
						<input class="form-control" type="text" name="name">
						<br>
						<input type="hidden" name="action" value="delete team">
						<input type="hidden" name="id" value="">
						<button type="submit" class="btn btn-lg btn-primary" >Delete team</button>
					</form>
				</div> 
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<!-- Modal -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h4 class="modal-title">Edit Tournament</h4>
      			</div>
				<div class="modal-body">
					<form class="form-signin" method="POST" action="TournamentManager">
            			<h2 class="form-signin-heading">Create New Tournament</h2>
                		<label> Tournament Name</label>
               			<input type="text" class="form-control" name="tournament_name" >
                		<br>
                		<label>Sport</label>
                		<select class="form-control" name="Sport">
                    	<option value="" disabled selected>Please select a sport</option>
                        <option value="soccer">Soccer</option>
                        <option value="tennis">Tennis</option>
                        <option value="pool">Pool</option>
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
                		<input type="text" class="form-control" name="size" >
                		<br>
                '		<label>Starting Date</label>
                		<input type="text" class="form-control" name="Starting Date">
                		<br>
                		<label>Info</label>
                		<input type="text" class="form-control" name="info">
                		<br>
                		<button class="btn btn-lg btn-primary " type="submit">Edit</button> 
        			</form>
	      		</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<!-- Modal -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h4 class="modal-title">Delete Tournament</h4>
      			</div>
				<div class="modal-body">
					<form method="POST" action="TeamManager">
						<input type="hidden" class="form-control" name="action" value="delete tournament">
						<input type="hidden" class="form-control" name="id" value="">
						<label class="form-control">Are you sure?</label>
						<br>
						<button class="btn btn-success btn-lg" data-dismiss="modal" >No</button>
						<button class="btn btn-lg btn-danger " type="submit">Yes</button> 	
					</form>
				</div>     
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

</body>
		
</html>