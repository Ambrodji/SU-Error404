<%@include file="newGameUpload.jsp" %>
<%@page import="dk.error404.dao.UserDao" %>
<%@page import="dk.error404.model.User" %>
<%@page import="java.util.ArrayList" %>
<%@include file="navigation.jsp" %>

<% 
String userId = request.getParameter("user");

if (userId == null) {
	System.out.println("user_edit: UserId not specified in URL, sending error");
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
}
UserDao userDao = new UserDao();
User user = userDao.findById(userId);

if (user == null) {
	System.out.println("user_edit: UserId not found in DB, sending error");
	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}

%>

// TODO: authorize client

<h1>User: <%=user.getId()%></h1>
<form>
  <fieldset class="form-group">
    <label for="exampleSelect1">User Type</label>
    <select class="form-control" id="userTypeSelect">
      <option <%=(user.getUserType().toLowerCase().equals("admin") ? "selected=\"selected\"" : "") %>>Admin</option>
      <option <%=(user.getUserType().toLowerCase().equals("ta") ? "selected=\"selected\"" : "") %>>TA</option>
      <option <%=(user.getUserType().toLowerCase().equals("student") ? "selected=\"selected\"" : "") %>>Student</option>
    </select>
  </fieldset>
  <fieldset class="form-group">
    <label for="userNameInput">Name</label>
    <input class="form-control" id="userNameInput" value="<%=user.getName()%>">
  </fieldset>
  <fieldset class="form-group">
    <label for="userEmailInput">Email address</label>
    <input type="email" class="form-control" id="userEmailInput" value="<%=user.getEmail()%>">
  </fieldset>
  <fieldset class="form-group">
    <label for="userSchoolInput">School</label>
    <input class="form-control" id="userSchoolInput" value="<%=user.getSchool()%>">
  </fieldset>

  <button type="submit" class="btn btn-primary">Save Changes</button>
</form>
