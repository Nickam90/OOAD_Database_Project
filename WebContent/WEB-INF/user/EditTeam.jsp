<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="service.ErrorService"
	import="dto.TeamInfoDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	TeamInfoDTO tDTO = (TeamInfoDTO) session.getAttribute("team");
%>
<%
	String teamLeader = (String) session.getAttribute("teamLeader");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Team</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style type="text/css"></style>
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
	<div class="container">
	
	<h3>Edit Team</h3>
		<p>
			<%
				out.print("Team Name: " + tDTO.getTeamName());
			%>
		</p>
		<p>
			<%
				out.print("Team Leader: " + teamLeader);
			%>
		</p>
		<p>
			<%
				out.print("Sport: " + tDTO.getSport());
			%>
		</p>
		<div class="col-md-6 list-group">
			<ul>
				 ${memberList}
			</ul>
					<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addModal">Add Member</button>
					<button class="btn btn-warning btn-lg" data-toggle="modal" data-target="#editModal">Edit Team</button>
					<button class="btn btn-danger btn-lg" data-toggle="modal" data-target="#deleteModal">Delete Team</button>
		</div>
		<br>
		<%
			ErrorService error = (ErrorService) request.getAttribute("error");
		%>
		<%
			if (error != null)
				out.println(error.getError());
		%>
	</div>
<!-- Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <form method="POST" action="TeamManager" >
        <label>Member name</label>
        <input class="form-control" type="text" name="name">
        <br>
		<input type="hidden" name="action" value="add member">
		<input type="hidden" name="id" value="<%= tDTO.getTeamId()%>">
		<button type="submit" class="btn btn-lg btn-primary" >Add member</button>
					
					
				</form>
      </div>
     
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
         	<form method="POST" action="TeamManager">
                <label> Team Name</label>
                <input type="text" class="form-control" name="team_name" value="<%= tDTO.getTeamName() %>">
              	<input type="hidden" class="form-control" name="action" value="edit team">
				<input type="hidden" class="form-control" name="id" value="<%= tDTO.getTeamId()%>">
              	<br>
            	<label>Sport</label>
                <select class="form-control" name="Sport">
                <option value="" disabled selected>Please select a sport</option>
                <option value="soccer">Soccer</option>
                <option value="tennis">Tennis</option>
                <option value="pool">Pool</option>
                </select>        
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
      <div class="modal-body">
         	<form method="POST" action="TeamManager">
              	<input type="hidden" class="form-control" name="action" value="edit team">
				<input type="hidden" class="form-control" name="id" value="<%= tDTO.getTeamId()%>">
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