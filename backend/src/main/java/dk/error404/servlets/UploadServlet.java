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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dk.error404.dao.ProgramDao;
import dk.error404.model.Program;

/**
 * Servlet implementation class ProgramUploadServlet
 */
@WebServlet("/UploadProg")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("welcome");
		

		//ProgramDao dao = new ProgramDao();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String path = "/onlineTAprograms/";
		
		String fileName = request.getParameter("fileName");
		Part filePart = request.getPart("file");
		String name = request.getParameter("name");
		String uploadedBy = (String) request.getSession().getAttribute("user");
		String description = request.getParameter("description");
		String difficultyMinStr = request.getParameter("difficultyMin");
		String difficultyMaxStr = request.getParameter("difficultyMax");
		int difficultyMin = -1;
		int difficultyMax = -1;
		
		// User not logged in
		if (uploadedBy == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		if (fileName == null || name == null || description == null || difficultyMinStr == null || difficultyMaxStr == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		try {
			difficultyMin = Integer.parseInt(difficultyMinStr);
			difficultyMax = Integer.parseInt(difficultyMaxStr);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		OutputStream out = null;
		InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();
	    
	    boolean error = false;
	    try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        // print debug info
	    } catch (FileNotFoundException fne) {
	    	System.out.println("UploadServlet: encountered error during file upload: " + fne.getMessage());
	        error = true;
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }
	    if (!error) {
	    	ProgramDao dao = new ProgramDao();
	    	
	    	Program prog = new Program();
	    	prog.setName(name);
	    	prog.setDescription(description);
	    	prog.setFileName(fileName);
	    	prog.setUploadedBy(uploadedBy);
	    	prog.setDifficultyMin(difficultyMin);
	    	prog.setDifficultyMax(difficultyMax);
	    	
	    	dao.insert(prog);
	    	System.out.println("UploadServlet: Inserted program with name=" + name);
	    } else {
	    	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	    }
		
	}

}
