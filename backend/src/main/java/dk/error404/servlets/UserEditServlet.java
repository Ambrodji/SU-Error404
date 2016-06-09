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
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/editUser")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authenticatedUser = (String) request.getSession().getAttribute("user");
		String userType = request.getParameter("userType");
		String userName = request.getParameter("user");
		String userID = request.getParameter("id");
		String email = request.getParameter("email");
		String school = request.getParameter("school");
		
		System.out.println("UserEditServlet: Received update request");
		System.out.println(userType);
		System.out.println(userName);
		System.out.println(userID);
		System.out.println(email);
		System.out.println(school);
		
		if (userName == null || userID == null || email == null || school == null ||
			"".equals(userName.trim()) || "".equals((userID).trim())) {
			System.out.println("UserEditServlet: Returning error because of missing fields");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		if (authenticatedUser == null) {
			System.out.println("user_overview: The client accessing this page was not authenticated, sending error");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		UserDao dao = new UserDao();
		User user = dao.findById(userID);
		User authUser = dao.findById(authenticatedUser);
		if (user == null) {
			System.out.println("UserEditServlet: UserID was not found in DB. Returning error 500");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		if (authUser == null) {
			System.out.println("UserEditServlet: The user that is logged in was not found in DB, sending error");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		
		if (authUser.getUserType().equals("admin") // Admin can always edit users
				|| (authUser.getUserType().equals("ta") && !user.getUserType().equals("admin")) // A TA can edit other users, except for admins
				|| (authUser.getId().equals(user.getId()))) { // A user can edit him/herself
			if (!userName.trim().equals("null") && !userName.trim().equals("")) {
				user.setName(userName);
			}
			if (!email.trim().equals("null") && !email.trim().equals("")) {
				user.setEmail(email);
			}
			if (!school.trim().equals("null") && !school.trim().equals("")) {
				user.setSchool(school);
			}
			if (userType != null) {
				System.out.println("usertype = " + userType);
				// If the userType doesnt match any known users, set it to 'student'
				if (!userType.toLowerCase().equals("admin") 
						|| !userType.toLowerCase().equals("ta") 
						|| !userType.toLowerCase().equals("student")) {
					userType = "student";
				}
				if (authUser.getUserType().equals("admin")) {
					user.setUserType(userType);
				} else if (authUser.getUserType().equals("ta") && !user.getUserType().equals("admin") && !userType.equals("admin")) {
					user.setUserType(userType);
				}
			}
			System.out.println("got user: " + user.getUserType());
			dao.update(user);
			System.out.println("UserEditServlet: Updating user: " + user.getId());
			
			PrintWriter writer = response.getWriter();
			writer.write(Conf.getInstance().getAjaxSuccess());
		} else {
			System.out.println("UserEditServlet: User " + authUser.getId() + " is not allowed to edit user " + user.getId() + ". Sending error");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
	}

}
