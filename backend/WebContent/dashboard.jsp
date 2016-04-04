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

<%@include file="newGameUpload.jsp" %>
<div id="page-wrapper" class="container-fluid">
					<h1>Active games:</h1>
					<table class="table">
						<thead>
							<tr>
								<th>Game</th>
								<th>TA</th>
								<th>Deadline</th>
								<th>Completed</th>
								<th>Progress</th>
								<th>
								<div class="btn-toolbar">
									<div class="btn-group">
  										<button type="button" class="btn btn-success" data-toggle="modal" data-target="#uploadGame"><span style="font-size:1em" class="glyphicon glyphicon-plus"></span></button>
								  		<button type="button" class="btn btn-danger"><span style="font-size:1em" class="glyphicon glyphicon-minus"></span></button>
								  		<button type="button" class="btn btn-info"><span style="font-size:1em" class="glyphicon glyphicon-signal"></span></button>
									</div>
								</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<td>Game 1</td>
							<td>Din mor</td>
							<td>02.03.2016</td>
							<td>Aldrig</td>
							</tr>
							<tr>
							<td>Game 1</td>
							<td>Din mor</td>
							<td>02.03.2016</td>
							<td>Aldrig</td>
							</tr>
							<tr>
							<td>Game 1</td>
							<td>Din mor</td>
							<td>02.03.2016</td>
							<td>Aldrig</td>
							</tr>
						</tbody>
					</table>				
				</div>
</body>
</html>