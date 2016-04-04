package dk.error404.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProgramHandler {
	private static ProgramHandler instance = null;
	private ProgramHandler() {}
	
	public static ProgramHandler getInstance() {
		if (instance == null) {
			instance = new ProgramHandler();
		}
		return instance;
	}
	
	public String getQuestion() {
		String response = "";
		try {
			Process process;
			process = new ProcessBuilder("C:\\game1.exe","getQuestion","1").start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			
			while ((line = br.readLine()) != null) {
			  response = response + line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
		
	}
}
