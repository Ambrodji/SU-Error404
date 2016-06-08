<%@include file="newGameUpload.jsp" %>
<%@page import="dk.error404.dao.UserDao" %>
<%@page import="dk.error404.model.User" %>
<%@page import="java.util.ArrayList" %>
<%@include file="navigation.jsp" %>

<% 
String userId = request.getParameter("user");
String authenticatedUser = (String) request.getSession().getAttribute("user");

if (userId == null) {
	System.out.println("user_edit: UserId not specified in URL, sending error");
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
}
if (authenticatedUser == null) {
	System.out.println("user_overview: The client accessing this page was not authenticated, sending error");
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
	return;
}
UserDao userDao = new UserDao();
User user = userDao.findById(userId);

if (user == null) {
	System.out.println("user_edit: UserId not found in DB, sending error");
	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}

if ((authenticatedUser.equals(user.getId()))) {%>
	<h1>Edit Your Profile</h1>
<%} else { %>
	<h1>Edit Profile: <%=user.getId()%></h1>
<% } %>

<form id="userEditForm">
  <fieldset class="form-group">
    <input class="form-control" style="display:none;" id="userIdInput" name="id" value="<%=user.getId()%>">
  </fieldset>
  <fieldset class="form-group">
    <label for="userTypeSelect">User Type</label>
    <select class="form-control" id="userTypeSelect">
      <option <%=(user.getUserType().toLowerCase().equals("admin") ? "selected=\"selected\"" : "") %>>Admin</option>
      <option <%=(user.getUserType().toLowerCase().equals("ta") ? "selected=\"selected\"" : "") %>>TA</option>
      <option <%=(user.getUserType().toLowerCase().equals("student") ? "selected=\"selected\"" : "") %>>Student</option>
    </select>
  </fieldset>
  <fieldset class="form-group">
    <label for="userNameInput">Name</label>
    <input class="form-control" id="userNameInput" name="user" value="<%=user.getName()%>">
  </fieldset>
  <fieldset class="form-group">
    <label for="userEmailInput">Email address</label>
    <input type="email" class="form-control" name="email" id="userEmailInput" value="<%=user.getEmail()%>">
  </fieldset>
  <fieldset class="form-group">
    <label for="userSchoolInput">School</label>
    <input class="form-control" id="userSchoolInput" name="school" value="<%=user.getSchool()%>">
  </fieldset>
  <div id="userEditSuccess" style="display:none;" class="alert alert-success" role="alert"><b>Your changes have been saved!</b></div>
  <div id="userEditError" style="display:none;" class="alert alert-danger" role="alert"><b>It was not possible to save the changes. Please try again later</b></div>
  <button type="submit" class="btn btn-primary">Save Changes</button>
</form>

<script>
$(document).ready(function(){
	$("#userEditForm").submit(function(event) {
		event.preventDefault();
	    $.ajax({
        	url: "editUser",
        	type: 'post',
        	dataType: 'text',
        	data: $("#userEditForm").serialize(),
        	success: function(data) {
        		if (data == "success") {
        			$("#userEditSuccess").css('display', 'block');
        		} else {
        			$("#userEditError").css('display', 'block');
        		}
            },
            error: function(data) {
            	$("#userEditError").css('display', 'block');
            }
        });
	});
});
</script>
