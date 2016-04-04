package dk.error404.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.error404.dao.UserDao;
import dk.error404.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/* Authenticates and redirects users */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("user");
		String password = request.getParameter("password");
		
		if (userID == null || password == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		
		UserDao dao = new UserDao();
		User user = dao.findById(userID);
		if (user != null && user.getPassword().equals(password)) {
			request.getSession().setAttribute("user", userID);
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

}
