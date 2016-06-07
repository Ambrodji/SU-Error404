<%@include file="newGameUpload.jsp" %>
<%@page import="dk.error404.dao.UserDao" %>
<%@page import="dk.error404.model.User" %>
<%@page import="java.util.ArrayList" %>
<%@include file="navigation.jsp" %>
<% 
String userId = request.getParameter("user");

if (userId == null) {
	System.out.println("user_overview: UserId not specified in URL, sending error");
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
}
UserDao userDao = new UserDao();
User user = userDao.findById(userId);

if (user == null) {
	System.out.println("user_overview: UserId not found in DB, sending error");
	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}

%>
<h1>User: <%=user.getId()%></h1>
<b>User type:</b>
<br />
<%=user.getUserType() %>
<br /><br />
<b>Name:</b>
<br />
<%=user.getName() %>
<br /><br />
<b>Email:</b>
<br />
<%=user.getEmail() %>
<br /><br />
<b>School:</b>
<br />
<%=user.getSchool() %>
<br /><br />
<button id="editProfileBtn" data-href='<%="?user=" + user.getId() %>' class="btn btn-primary">Edit Profile</button>


						
							

					