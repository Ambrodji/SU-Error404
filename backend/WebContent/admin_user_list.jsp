<%@include file="newGameUpload.jsp" %>
<%@include file="gameDelete.jsp" %>
<%@include file="navigation.jsp" %>
<%@page import="dk.error404.dao.UserDao" %>
<%@page import="dk.error404.model.User" %>
<%@page import="java.util.ArrayList" %>

<h1>Currently registered users:</h1>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>School</th>
			<th>Type</th>
		</tr>
	</thead>
	<tbody>
		<% 
		UserDao dao = new UserDao(); 
		ArrayList<User> userList = dao.findAll();
		for (User u : userList) {%>
			<tr class="userRow" data-href='<%="?user=" + u.getId() %>' >
				<td><%=u.getId() %></td>
				<td><%=u.getName() %></td>
				<td><%=u.getEmail() %></td>
				<td><%=u.getSchool() %></td>
				<td><%=u.getUserType() %></td>
			</tr>
		<%}%>
							
	</tbody>
</table>				