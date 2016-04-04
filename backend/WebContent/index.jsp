<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" />
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<title>OnlineTA</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	
	<% boolean authenticated = true; %>
	<br/><br/>
	<div id="wrapper" >
		<% if (authenticated) { %>
			<%@include file="menu_authenticated.jsp" %>
		<% }else{ %>
			<%@include file="menu_unauthenticated.jsp" %>
		<% } %>
		
		<iframe class="embed-responsive-item" name="mainWindow" src="dashboard.jsp" frameborder="0" scrolling="no"></iframe>
				
	</div>

</body>
</html>