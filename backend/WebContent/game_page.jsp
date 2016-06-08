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
	response.sendError(500);
	return;
}
try {
	gameId = Integer.parseInt(gameIdStr);
	gameDifficulty = Integer.parseInt(gameDifficultyStr);
} catch (NumberFormatException e) {
	System.out.println("game_overview.jsp: Unable to parse request arguments");
	response.sendError(500);
	return;
}
ProgramDao dao = new ProgramDao();
Program prog = dao.findById(gameId);

%>
<h1><%=prog.getName()%></h1>
<b>Description</b>
<br />
<%=prog.getDescription() %>
<br /><br />
<b>Question</b>
<br />
<p id="questionText">Loading....</p>

<div id="multipleChoice" style="display:none;">
	<b>Please select an answer:</b>
	<br />
	<table class="table table-striped table-hover" id="multipleChoiceTable">
	</table>
</div>
<div id="basicAnswer" style="display:none;">
	<form id="basicAnswerForm" role="form">
		<div class="form-group">
			<label for="textAnswer">Please submit your answer in the field below:</label>
			<input type="text" id="textAnswer" class="form-control">
		</div>
		<button type="submit" id="submitAnswerBtwn" class="btn btn-success">Submit Answer</button>
	</form>
</div>
<div id="correctAnswer" style="display:none;" class="alert alert-success" role="alert"><b>Your answer was correct!</b></div>
<div id="incorrectAnswer" style="display:none;" class="alert alert-danger" role="alert"><b>Your answer was incorrect!</b></div>
<button id="nextQuestionBtn" style="display:none;" class="btn btn-primary">Next Question</button>

<script>
	var queryUrl = 'GameServlet';
	var queryString = '<%="?game=" + gameId %>&difficulty=<%=gameDifficulty%>';
	var questionId = -1;
	
	$(document).ready(function(){
		getQuestion();
		$("#basicAnswerForm").submit(function(event) {
			event.preventDefault();
			var answer = $("#textAnswer").val();
			$("#textAnswer").addClass('uneditable-input');
			$("#submitAnswerBtwn").css('display', 'none');
			submitAnswer(answer);
		});
		$("#nextQuestionBtn").click(function() {
			getQuestion();
		});
	});
	
	function submitAnswer(answer) {
		$.ajax({
            url: queryUrl,
            type: 'post',
            dataType: 'text',
            data: {'answer' : answer, 'questionId' : questionId},
            success: function(data) {
                showFeedback(data);
            },
            error: function(data) {
            	alert("Failed to submit question to server" + data);
            	getQuestion();
            }
    	});
		
	}
	
	function showFeedback(data) {
		if (data == "correct") {
			$("#correctAnswer").css('display', 'block');
		} else {
			$("#incorrectAnswer").css('display', 'block');
		}
		$("#nextQuestionBtn").css('display', 'inline');
	}
	
	function getQuestion() {
		showLoading();
		
		$.get(queryUrl + queryString, function(data){
			if (data == "jsonerror") {
				$("p#questionText").html("Error: The game returned an invalid JSON string. Please contact your system administrator or TA");
				return;
			}
			$("p#questionText").html(data.question);
			questionId = data.questionId;
			
			if ($.isEmptyObject(data.choices)) {
				startSingleChoice(data);
			} else {
				startMultipleChoice(data);
			}
		});
	}
	
	function showLoading() {
		$("questionText").text("Loading...");
		$("#multipleChoiceTable").addClass('table-hover')
		$("#submitAnswerBtwn").css('display', 'block');
		$("#correctAnswer").css('display', 'none');
		$("#incorrectAnswer").css('display', 'none');
		$("#nextQuestionBtn").css('display', 'none');
		$("#basicAnswer").css('display', 'none');
		$("#multipleChoice").css('display', 'none');
		$("#multipleChoiceTable").empty();
		$("#textAnswer").val("");
	}
	
	function startMultipleChoice(data) {
		$("#multipleChoice").css('display', 'inline');
		
		$.each(data.choices, function(i, item) {
			$("#multipleChoiceTable")
				.append("<tbody><tr class=\"multipleChoiceRow\"><td>" + item + "</td></tr></tbody>")
		});
		
		$(".multipleChoiceRow").click(function() {
			var answer = $(this).text();
		
			$("#multipleChoiceTable").removeClass('table-hover')
			if (!$(this).hasClass('disabled')) {
				submitAnswer(answer);
			}
			$(".multipleChoiceRow").addClass('disabled');
		});
	}
	
	function startSingleChoice(data) {
		$("#basicAnswer").css('display', 'inline');
	}


</script>



					