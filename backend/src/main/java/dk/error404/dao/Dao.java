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
		initializeTeamTable();
		initializeTeamParticipantTable();
		initializeAssignmentTable();
		initializeQuestionTable();
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
	
	private void initializeAssignmentTable() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS TEAM_PARTICIPANT " +
	    			"(ID 			INT 	PRIMARY KEY	NOT NULL," +
	    			" NAME			TEXT	NOT NULL, " + 
	    			" DESCRIPTION	TEXT, " +
	    			" FEEDBACK		TEXT, " +
	    			" TEAM_ID		INT		NOT NULL,  " +
	    			" DEADLINE		TEXT); ";
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
	    			" ASSIGNMENT_ID	INT		NOT NULL, " + 
	    			" QUESTION_TEXT	TEXT	NOT NULL, " +
	    			" QUESTION_TYPE	TEXT	NOT NULL, " +
	    			" OPTIONS		TEXT, " +
	    			" ANSWER		TEXT, " +
	    			" TS_COMPLETED	TEXT); ";
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
	
    public abstract T create(ResultSet result)
    	throws SQLException
    ;
    
    protected void executeInsert(String sql, Dao.ParameterSetter parameters) {
    	Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        public void setParameters(PreparedStatement statement)
            throws SQLException
        ;
    }

}
