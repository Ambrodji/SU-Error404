<%@include file="newGameUpload.jsp" %>
<%@page import="dk.error404.dao.ProgramDao" %>
<%@page import="dk.error404.model.Program" %>
<%@page import="java.util.ArrayList" %>
<% 
String gameIdStr = request.getParameter("game");
String gameDifficultyStr = request.getParameter("difficulty");
int gameId = -1;
int gameDifficulty = -1;
if (gameIdStr == null) {
	System.out.println("game_overview.jsp: Missing game id str");
}
try {
	gameId = Integer.parseInt(gameIdStr);
	gameDifficulty = Integer.parseInt(gameDifficultyStr);
} catch (NumberFormatException e) {
	System.out.println("game_overview.jsp: Unable to parse request arguments");
}
ProgramDao dao = new ProgramDao();
Program prog = dao.findById(gameId);

%>
<h1>Overview - <%=prog.getName()%></h1>
<b>Question</b>
</br>
<%=prog.getDescription() %>
</br></br>
<b>Please select an answer:</b>
</br>

<table class="table table-striped table-hover">
<%
for (int i = 1; i <= 5; i++) {%>
	<tbody>
		<tr class="answerRow" data-href='<%="?game=" + gameId %>&difficulty=<%=i%>' >
			<td>Answer <%=i %></td>
		</tr>
	</tbody>
<% } %>
</table>

					