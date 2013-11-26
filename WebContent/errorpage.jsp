<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String error = (String) request.getAttribute("error"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Errorpage</title>
</head>
<body>
<h1>Invalid URL</h1>
<p>The URL that you requested is not available  on that server.</p>
<% out.print("<p>" + error + "</p>"); %>

<button type="button" name="back" onclick="history.back()">back</button>
</body>
</html>