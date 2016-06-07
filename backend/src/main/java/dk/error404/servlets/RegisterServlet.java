package dk.error404.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.error404.control.Conf;
import dk.error404.dao.UserDao;
import dk.error404.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/* Used to check if a user already exists */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("user");
		if (userID != null) {
			UserDao dao = new UserDao();
			User user = dao.findById(userID);
			if (user != null) {
				response.getWriter().println("unavailable");
				return;
			}
			response.getWriter().println("available");
		}
	}

	/* Used to actually register the user */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user");
		String password = request.getParameter("password");
		String userID = request.getParameter("id");
		String email = request.getParameter("email");
		String school = request.getParameter("school");
		
		if (userName == null || password == null || userID == null || email == null || school == null ||
			"".equals(userName.trim()) || "".equals((userID).trim()) || "".equals(password.trim())) {
			System.out.println("RegisterServlet: Returning error because of missing fields");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		UserDao dao = new UserDao();
		User user = dao.findById(userID);
		if (user != null) {
			System.out.println("RegisterServlet: UserID already exists. Returning error 500");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		
		user = new User();
		
		user.setId(userID);
		user.setName(userName);
		user.setEmail(email);
		user.setPassword(password);
		user.setSchool(school);
		user.setUserType("student"); // A new user is always a student. An administrator/TA can change this in the "users" view if needed.
		
		dao.insert(user);
		response.getWriter().println(Conf.getInstance().getAjaxSuccess());
		System.out.println("RegisterServlet: Created user with id=" + userID);
		
		request.getSession().setAttribute("user", user.getId());
		response.sendRedirect(request.getContextPath() + "/");
		
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

}
