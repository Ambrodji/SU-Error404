<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<title>OnlineTA</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"></script>
	<%@include file ="navigation.jsp" %>
</head>
<body>
	<%
	boolean authenticated = false;
	String user = (String) request.getSession().getAttribute("user");
	if (user != null) {
		authenticated = true;
	}
	%>
	<br/><br/>
	<div id="wrapper">
		<% if (authenticated) { %>
			<%@include file="menu_authenticated.jsp" %>
		<% }else{ %>
			<%@include file="menu_unauthenticated.jsp" %>
		<% } %>
	
		<div id="page-wrapper" class="container-fluid">
			<% if (authenticated) { %>
				<%@include file="dashboard.jsp" %>
			<% }else{ %>
				<img src="OnlineTAimage.png" style="position:absolute; TOP:50px; LEFT:128px; WIDTH:80%">
			<% } %>
		</div>
				
	</div>

</body>
</html>