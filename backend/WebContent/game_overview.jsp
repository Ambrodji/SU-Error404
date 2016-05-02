<%@include file="newGameUpload.jsp" %>
<%@page import="dk.error404.dao.ProgramDao" %>
<%@page import="dk.error404.model.Program" %>
<%@page import="java.util.ArrayList" %>
<%@include file="navigation.jsp" %>
<% 
String gameIdStr = request.getParameter("game");
int gameId = -1;
if (gameIdStr == null) {
	System.out.println("game_overview.jsp: Missing game id str");
}
try {
	gameId = Integer.parseInt(gameIdStr);
} catch (NumberFormatException e) {
	System.out.println("game_overview.jsp: Unable to parse string as int");
}
ProgramDao dao = new ProgramDao();
Program prog = dao.findById(gameId);

%>
<h1>Overview - <%=prog.getName()%></h1>
<b>Description</b>
</br>
<%=prog.getDescription() %>
</br></br>
<b>Please select a difficulty:</b>
</br>

<table class="table table-striped table-hover">
<%
for (int i = 1; i <= prog.getDifficulties(); i++) {%>
	<tbody>
		<tr class="difficultyRow" data-href='<%="?game=" + gameId %>&difficulty=<%=i%>' >
			<td>Level <%=i %></td>
		</tr>
	</tbody>
<% } %>
</table>

						
							

					