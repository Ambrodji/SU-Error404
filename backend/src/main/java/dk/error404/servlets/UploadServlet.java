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
@WebServlet("/UploadProg")
@MultipartConfig
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("file");
		//String fileName = getSubmittedFileName(filePart);
		String name = request.getParameter("name");
		String uploadedBy = (String) request.getSession().getAttribute("user");
		String description = request.getParameter("description");
		String difficultiesStr = request.getParameter("difficulties");
		int difficulties = -1;
		
		// User not logged in
		if (uploadedBy == null) {
			System.out.println("UploadServlet: No user logged in, sending error");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		if (name == null || description == null || difficultiesStr == null) {
			System.out.println("UploadServlet: Missing fields, sending error");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		try {
			difficulties = Integer.parseInt(difficultiesStr);
		} catch (NumberFormatException e) {
			System.out.println("UploadServlet: Failed to parse difficulty, sending error");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		OutputStream out = null;
		InputStream filecontent = null;
		File dir = new File(Conf.getInstance().getProgramDir());
		dir.mkdirs();
		
		File outFile = File.createTempFile(uploadedBy, ".exe", dir);
	    
	    boolean error = false;
	    try {
	    	out = new FileOutputStream(outFile);
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
	    }
	    if (!error) {
	    	ProgramDao dao = new ProgramDao();
	    	
	    	Program prog = new Program();
	    	prog.setName(name);
	    	prog.setDescription(description);
	    	prog.setFileName(outFile.getName());
	    	prog.setUploadedBy(uploadedBy);
	    	prog.setDifficulties(difficulties);
	    	
	    	dao.insert(prog);
	    	
	    	response.getWriter().println("success");
	    	System.out.println("UploadServlet: Inserted program with name=" + name);
			response.setStatus(HttpServletResponse.SC_CREATED);
			
	    	return;
	    } else {
	    	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	    	return; 
	    }
		
	}
	
	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}

}
