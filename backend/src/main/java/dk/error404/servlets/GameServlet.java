package dk.error404.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.google.gson.*;

import dk.error404.dao.ProgramDao;
import dk.error404.dao.QuestionDao;
import dk.error404.model.Program;
import dk.error404.model.Question;
import dk.error404.model.QuestionAPIDto;
import dk.error404.model.QuestionDbDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CORRECT_ANSWER = "correct";
	private static final String INCORRECT_ANSWER = "incorrect";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // Used to fetch new questions
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameIdStr = request.getParameter("game");
		String gameDifficultyStr = request.getParameter("difficulty");
		int gameId = -1;
		int gameDifficulty = -1;
		String questionStr = "";
		if (gameIdStr == null) {
			System.out.println("GameServlet: Missing game id str in /GET");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		try {
			gameId = Integer.parseInt(gameIdStr);
			gameDifficulty = Integer.parseInt(gameDifficultyStr);
		} catch (NumberFormatException e) {
			System.out.println("GameServlet: Unable to parse request arguments in /GET");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		ProgramDao dao = new ProgramDao();
		Program prog = dao.findById(gameId);
		
		if (prog != null) {
			Process process = new ProcessBuilder(UploadServlet.PROGRAM_PATH + prog.getFileName(),"getQuestion", gameDifficulty + "").start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			while ((line = br.readLine()) != null) {
				questionStr += line;
			}
		} else {
			System.out.println("GameServlet: A program with the given ID could not be found in DB (/GET)");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		Gson gson = new Gson();
		QuestionDbDto questionDbDto = gson.fromJson(questionStr, QuestionDbDto.class);
		
		QuestionDao qDao = new QuestionDao();
		Question question = new Question();
		
		question.setAnswer(questionDbDto.getAnswer());
		question.setQuestionOptions(""); // TODO: fix
		question.setQuestionText(questionDbDto.getQuestion());
		if (questionDbDto.getChoices() != null) {
			String questionOptionsStr = "";
			List<String> questionOptions = questionDbDto.getChoices();
			for (int i = 0; i < questionOptions.size(); i++) {
				questionOptionsStr = questionOptionsStr + questionOptions.get(i) + Question.OPTIONS_DELIMITER;
			}
			question.setQuestionOptions(questionOptionsStr);
		}
		
		if (request.getSession().getAttribute("user") != null) {
			question.setUserId((String) request.getSession().getAttribute("user"));
		}
		int questionId = qDao.insert(question);
		
		QuestionAPIDto questionAPIDto = new QuestionAPIDto();
		
		String DbDtoQuestion = questionDbDto.getQuestion();
		
		// Prevent NPE
		DbDtoQuestion = (DbDtoQuestion == null) ? "" : DbDtoQuestion;
		
		// Replace newlines with proper HTML linebreaks
		String htmlQuestionStr = DbDtoQuestion.replace("\n", " <br /> ");
		
		// Replace multiple spaces with &nbsp for correct HTML spacing
		htmlQuestionStr = htmlQuestionStr.replace("  ",  " &nbsp&nbsp ");
		
		questionAPIDto.setQuestion(htmlQuestionStr);
		questionAPIDto.setQuestionId(questionId);
		questionAPIDto.setChoices(questionDbDto.getChoices());
		String result = gson.toJson(questionAPIDto);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println("GameServlet: writing response: " + result);
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// Used to evaluate an answer
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String answer = request.getParameter("answer");
		String questionIdStr = request.getParameter("questionId");
		int questionId = -1;
		if (answer == null || questionIdStr == null) {
			System.out.println("GameServlet: Missing answer or questionId in /POST");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		try {
			questionId = Integer.parseInt(questionIdStr);
		} catch (NumberFormatException e) {
			System.out.println("GameServlet: Unable to parse questionId in /POST");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		QuestionDao dao = new QuestionDao();
		Question q = dao.findById(questionId);
		// Find the question associated with the request
		if (q != null) {
			String dbAnswer = q.getAnswer();
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			q.setTsCompleted(ts.toString());
			dao.update(q);
			
			// See if an answer already exists in DB. Fetch an answer from the program if not
			if (dbAnswer != null) {
				PrintWriter writer = response.getWriter();
				if(dbAnswer.trim().equals(answer.trim())) {
					writer.write(CORRECT_ANSWER);
					return;
				} else {
					writer.write(INCORRECT_ANSWER);
					return;
				}
			} else {
				// Fetch
				System.out.println("GameServlet: STUB. Not able to fetch missing question yet");
			}
		} else {
			System.out.println("GameServlet: Unable to find questionId in DB (/POST)");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		System.out.println("answer = " + answer);
	}

}
