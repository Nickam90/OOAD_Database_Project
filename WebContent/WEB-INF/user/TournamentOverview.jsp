<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Tournament Overview</title>
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
			<h2 class="text-muted">View Tournaments</h2>
			<hr>		
		</div>
	</div>
	<div class="container">
	<div class="col-md-6 list-group">
		<h2>Tournament List</h2>
		<ul>
		${tournList}
		</ul>
	</div>
</div>
</body>
</html>