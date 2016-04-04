package dk.error404.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Dao<T> {
	private final String DATABASE_NAME = "error404database.db";
	
	public Dao() {
		// Initialize tables in DB in case they don't exist already
		initializeTabels();
	}
	
	private void initializeTabels() {
		System.out.println("Initializing DB...");
		initializeUserTable();
		initializeUserTypeTable();
		initializeTeamTable();
		initializeTeamParticipantTable();
		initializeProgramTable();
		initializeQuestionTable();
		initializeQuestionTypeTable();
		System.out.println("DB loaded.");
	}
	
	private void initializeTeamTable() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS TEAM " +
	    			"(ID 			INT 	PRIMARY KEY	NOT NULL," +
	    			" NAME			TEXT	NOT NULL, " + 
	    			" DESCRIPTION	TEXT); ";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
	private void initializeTeamParticipantTable() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS TEAM_PARTICIPANT " +
	    			"(USER_ID		TEXT	NOT NULL, " +
	    			" TEAM_ID		INT		NOT NULL, " + 
	    			" ROLE			TEXT	NOT NULL, " + 
	    			" PRIMARY KEY (USER_ID, TEAM_ID));";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
	private void initializeProgramTable() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS PROGRAM " +
	    			"(ID 			INT 	PRIMARY KEY	NOT NULL," +
	    			" NAME			TEXT	NOT NULL, " + 
	    			" DESCRIPTION	TEXT, " +
	    			" FILE_NAME		TEXT	NOT NULL, " +
	    			" UPLOADED_BY	TEXT		NOT NULL,  " +
	    			" DIFFICULTY_MAX	INT		NOT NULL,  " +
	    			" DIFFICULTY_MIN	INT		NOT NULL);";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
	private void initializeQuestionTable() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS QUESTION " +
	    			"(ID 			INT 	PRIMARY KEY	NOT NULL," +
	    			" USER_ID		INT		NOT NULL, " + 
	    			" QUESTION_TYPE	INT	NOT NULL, " +
	    			" QUESTION_TEXT	TEXT	NOT NULL, " +
	    			" QUESTION_OPTIONS		TEXT, " +
	    			" ANSWER		TEXT, " +
	    			" FEEDBACK		TEXT, " +
	    			" TS_COMPLETED	TEXT); ";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
	private void initializeQuestionTypeTable() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS QUESTION_TYPE " +
	    			"(ID 			INT 	PRIMARY KEY	NOT NULL," +
	    			" DESCRIPTION			TEXT	NOT NULL); ";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
	private void initializeUserTable() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS USER " +
	    			"(ID 			TEXT 	PRIMARY KEY	NOT NULL," +
	    			" PASSWORD		TEXT	NOT NULL," +
	    			" NAME			TEXT, " + 
	    			" EMAIL			TEXT, " +
	    			" USER_TYPE		TEXT	NOT NULL," +
	    			" SCHOOL		TEXT);";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
	private void initializeUserTypeTable() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS USER_TYPE " +
	    			"(ID 			INT 	PRIMARY KEY	NOT NULL," +
	    			" DESCRIPTION			TEXT	NOT NULL); ";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
    public abstract T create(ResultSet result)
    	throws SQLException
    ;
    
    protected void executeInsert(String sql, Dao.ParameterSetter parameters) {
    	executeInsert(sql, parameters, null);
    }
    
    protected void executeInsert(String sql, Dao.ParameterSetter parameters, Dao.IdSetter idSetter) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (parameters!= null) {
                parameters.setParameters(statement);
            }
            statement.executeUpdate();
            if (idSetter!= null) {
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    idSetter.setId(key.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
   
    protected ArrayList<T> executeQuery(String sql, Dao.ParameterSetter parameters) {
        ArrayList<T> result = new ArrayList<T>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
            PreparedStatement statement = connection.prepareStatement(sql);
            if (parameters!= null) {
                parameters.setParameters(statement);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(create(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
    
    protected void executeUpdateDelete(String sql, Dao.ParameterSetter parameters) {
        Connection connection = null;
        try {
        	connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        	PreparedStatement statement = connection.prepareStatement(sql);
            if (parameters!= null) {
                parameters.setParameters(statement);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    public interface ParameterSetter {
        public void setParameters(PreparedStatement statement) throws SQLException;
    }
    
    public interface IdSetter {
        public void setId(int id);
    }

}
