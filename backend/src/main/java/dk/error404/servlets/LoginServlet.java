package dk.error404.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.error404.control.Conf;
import dk.error404.dao.UserDao;
import dk.error404.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	/* Authenticates and redirects users */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("user");
		String password = request.getParameter("password");
		System.out.println("Login servlet: authenticating user " + userID);
		
		PrintWriter writer = response.getWriter();
		
		if (userID == null || password == null) {
			System.out.println("LoginServlet: Missing either userID or password. Sending error");
			writer.write(Conf.getInstance().getAjaxError());
		} else {
			UserDao dao = new UserDao();
			User user = dao.findById(userID);
			if (user != null && user.getPassword().equals(password)) {
				request.getSession().setAttribute("user", userID);
				request.getSession().setAttribute("userType", user.getUserType());
				writer.write(Conf.getInstance().getAjaxSuccess());
			} else {
				writer.write(Conf.getInstance().getAjaxError());
				System.out.println("LoginServlet: No user with the given password could be found. Sending error");
			}
		}
		try {
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
