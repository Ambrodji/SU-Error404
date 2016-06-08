<%@include file="newGameUpload.jsp" %>
<%@page import="dk.error404.dao.UserDao" %>
<%@page import="dk.error404.model.User" %>
<%@page import="java.util.ArrayList" %>
<%@include file="navigation.jsp" %>
<% 
String userId = request.getParameter("user");
String authenticatedUser = (String) request.getSession().getAttribute("user");
String authenticatedUserType = (String) request.getSession().getAttribute("userType");

if (userId == null) {
	System.out.println("user_overview: UserId not specified in URL, sending error");
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	return;
}
if (authenticatedUserType == null || authenticatedUser == null) {
	System.out.println("user_overview: The client accessing this page was not authenticated, sending error");
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
	return;
}
UserDao userDao = new UserDao();
User user = userDao.findById(userId);
User authUser = userDao.findById(authenticatedUser);

if (user == null) {
	System.out.println("user_overview: UserId not found in DB, sending error");
	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	return;
}
if (authUser == null) {
	System.out.println("user_overview: The user that is logged in was not found in DB, sending error");
	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	return;
}

if ((authUser.getId().equals(user.getId()))) {%>
	<h1>Your Profile</h1>
<%} else { %>
	<h1>User: <%=user.getId()%></h1>
<% } %>
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
<% if (authUser.getUserType().equals("admin") // Admin can always edit users
		|| (authUser.getUserType().equals("ta") && !user.getUserType().equals("admin")) // A TA can edit other users, except for admins
		|| (authUser.getId().equals(user.getId()))) { // A user can edit him/herself
%>
<button id="editProfileBtn" data-href='<%="?user=" + user.getId() %>' class="btn btn-primary">Edit Profile</button>
<% } %>


						
							

					