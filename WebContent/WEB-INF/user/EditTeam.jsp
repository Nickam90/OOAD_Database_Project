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

	<script>
		function editTeam() {

		}
		function addMember() {

		}

		function deleteTeam() {

		}
	</script>

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
			<ul>
				<button class="btn btn-lg btn-primary" id="addbtn"onclick="addMember()">Add Member</button>
				<button class="btn btn-lg btn-primary" id="editbtn"onclick="editTeam()">Edit</button>
				<button class="btn btn-lg btn-primary" id="delbtn"onclick="deleteTeam()">Delete Team</button>
			</ul>
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

</body>
</html>