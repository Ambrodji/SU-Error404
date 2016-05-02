<%@include file="newGameUpload.jsp" %>
<%@page import="dk.error404.dao.ProgramDao" %>
<%@page import="dk.error404.model.Program" %>
<%@page import="java.util.ArrayList" %>

					<h1>Active games:</h1>
					<table class="table">
						<thead>
							<tr>
								<th>Game</th>
								<th>Description</th>
								<th>TA</th>
								<th>Completed</th>
								<th>Progress</th>
								<th>
								<% if (request.getSession().getAttribute("user") != null) { %>
								<div class="btn-toolbar">
									<div class="btn-group">
  										<button type="button" class="btn btn-success" data-toggle="modal" data-target="#uploadGame"><span style="font-size:1em" class="glyphicon glyphicon-plus"></span></button>
								  		<button type="button" class="btn btn-danger"><span style="font-size:1em" class="glyphicon glyphicon-minus"></span></button>
								  		<button type="button" class="btn btn-info"><span style="font-size:1em" class="glyphicon glyphicon-signal"></span></button>
									</div>
								</div>
								<% } %>
								</th>
							</tr>
						</thead>
						<tbody>
						<% ProgramDao dao = new ProgramDao(); 
						ArrayList<Program> programs = dao.findAll();
						for (Program p : programs) {%>
							<tr>
							<td><%=p.getName() %></td>
							<td><%=p.getDescription() %></td>
							<td><%=p.getUploadedBy() %></td>
							<td>-</td>
							<td>None</td>
							</tr>
						<%}%>
							
						</tbody>
					</table>				