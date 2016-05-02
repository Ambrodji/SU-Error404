package dk.error404.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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
			System.out.println("GameServlet: Missing game id str");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		try {
			gameId = Integer.parseInt(gameIdStr);
			gameDifficulty = Integer.parseInt(gameDifficultyStr);
		} catch (NumberFormatException e) {
			System.out.println("GameServlet: Unable to parse request arguments");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		ProgramDao dao = new ProgramDao();
		Program prog = dao.findById(gameId);
		
		if (prog != null) {
			Process process = new ProcessBuilder("OnlineTA_TrueFalseGame.exe","getQuestion", gameDifficulty + "").start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			while ((line = br.readLine()) != null) {
				questionStr += line;
			}
		} else {
			System.out.println("GameServlet: A program with the given ID could not be found in DB");
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
		if (request.getSession().getAttribute("user") != null) {
			question.setUserId((String) request.getSession().getAttribute("user"));
		}
		int questionId = qDao.insert(question);
		
		QuestionAPIDto questionAPIDto = new QuestionAPIDto();
		questionAPIDto.setQuestion(questionDbDto.getQuestion());
		questionAPIDto.setQuestionId(questionId);
		questionAPIDto.setChoices(questionDbDto.getChoices());
		String result = gson.toJson(questionAPIDto);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// Used to evaluate an answer
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
