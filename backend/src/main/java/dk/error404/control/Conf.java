package dk.error404.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conf {
	private static Conf instance = null;
	
	private final static String CONFIG_FILE = "config.properties";
	
	// The following are used in the front-end. Be careful if you change these
	private final static String AJAX_SUCCESS = "success";
	private final static String AJAX_ERROR = "error";
	private final static String ANSWER_CORRECT = "correct";
	private final static String ANSWER_INCORRECT = "incorrect";
	
	// The following fields are updated dynamically with config.properties
	private static String program_dir = "/error404/OnlineTAPrograms/";
	private static String database_name = "error404database.db";
	private static String database_dir = "/error404/";
	
	
	private Conf() {
		// Load configuration first time
		refreshConfiguration();
	}
	
	public static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}
	
	// Used to refresh configuration. Is called initially
	// TODO: Should be possible to invoke using a servlet
	public void refreshConfiguration() {
		System.out.println("Conf: Loading configuration from " + CONFIG_FILE + "...");
		Properties properties = null;

		try {
		    properties = new Properties();
		    InputStream resourceAsStream =  Conf.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
		    if (resourceAsStream != null) {
		    	
		        properties.load(resourceAsStream);
		        if (properties.getProperty("programDir") != null) {
		        	program_dir = properties.getProperty("programDir");
		        	System.out.println("Conf: Updated programDir. Value = " + program_dir);
		        }
		        if (properties.getProperty("databaseDir") != null) {
		        	database_dir = properties.getProperty("databaseDir");
		        	System.out.println("Conf: Updated databaseDir. Value = " + database_dir);
		        }
		        if (properties.getProperty("databaseName") != null) {
		        	database_name = properties.getProperty("databaseName");
		        	System.out.println("Conf: Updated databaseName. Value = " + database_name);
		        }
		        resourceAsStream.close();
		    }
		    System.out.println("Conf: Successfully updated configuration");

		} catch (IOException e) {
			System.out.println("Conf: An error occurred during configuration loading");
		    e.printStackTrace();
		}

		
	}
	
	public String getProgramDir() {
		return program_dir;
	}
	
	public String getDatabaseDir() {
		return database_dir;
	}
	
	public String getDatabaseName() {
		return database_name;
	}
	
	public String getDatabasePath() {
		return database_dir + database_name;
	}
	
	public String getAjaxSuccess() {
		return AJAX_SUCCESS;
	}
	
	public String getAjaxError() {
		return AJAX_ERROR;
	}
	
	public String getAnswerCorrect() {
		return ANSWER_CORRECT;
	}
	
	public String getAnswerIncorrect() {
		return ANSWER_INCORRECT; 
	}
	
}
