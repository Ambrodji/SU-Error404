package dk.error404.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.security.auth.login.Configuration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dk.error404.control.Conf;
import dk.error404.dao.ProgramDao;
import dk.error404.model.Program;

/**
 * Servlet implementation class ProgramUploadServlet
 */
@WebServlet("/DeleteProg")
@MultipartConfig
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String programIdStr = request.getParameter("programId");
		String userId = (String) request.getSession().getAttribute("user");
		
		// User not logged in
		if (userId == null) {
			System.out.println("DeleteServlet: No user logged in, sending error");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		if (programIdStr == null) {
			System.out.println("DeleteServlet: Program Id not specified, sending error");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		int programId;
		try {
			programId = Integer.parseInt(programIdStr);
		} catch (NumberFormatException e) {
			System.out.println("DeleteServlet: Failed to parse programId, sending error");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		ProgramDao dao = new ProgramDao();
		Program program = dao.findById(programId);
		
		PrintWriter writer = response.getWriter();
		if (program != null) {
			File file = new File(Conf.getInstance().getProgramDir() + program.getFileName());
			System.out.println("DeleteServlet: Deleting program with path=" + file.getAbsolutePath());
			
			if (file.delete()) {
				System.out.println("DeleteServlet: Deleting program with id=" + programId + " from DB");
				dao.delete(program);
				writer.write(Conf.getInstance().getAjaxSuccess());
				return;
			} else {
				System.out.println("DeleteServlet: Deletion error. It was not possible to delete program with path="+ file.getAbsolutePath());
				writer.write(Conf.getInstance().getAjaxError());
				return;
			}
		} else {
			System.out.println("DeleteServlet: Failed to find program in DB with id=" + programId);
			writer.write(Conf.getInstance().getAjaxError());
			return;
		}
		
		
	}

}
