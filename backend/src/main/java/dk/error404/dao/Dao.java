package dk.error404.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dk.error404.model.User;

public abstract class Dao<T> {
	private final String DATABASE_NAME = "error404database.db";
	private static boolean initialized = false;
	
	public Dao() {
		// Initialize tables in DB in case they don't exist already
		if (!initialized) {
			System.out.println("Initializing DB...");
			initializeTabels();
			initialized = true;
			insertTestUser();
		}
	}
	
	private boolean databaseExists()
	{
		File dbFile = new File(DATABASE_NAME);
	    return dbFile.exists();
		/*Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "SELECT COUNT(*) FROM USER;";
	    	stmt.executeQuery(sql);
	    	ResultSet results = stmt.getResultSet();
	    	results.ne
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }*/
	}
	
	private void initializeTabels() {
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
	    			"(ID 			INTEGER	PRIMARY KEY	AUTOINCREMENT," +
	    			" NAME			TEXT	NOT NULL, " + 
	    			" DESCRIPTION	TEXT, " +
	    			" FILE_NAME		TEXT	NOT NULL, " +
	    			" UPLOADED_BY	TEXT		NOT NULL,  " +
	    			" DIFFICULTIES	INTEGER		NOT NULL);";
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
	    			"(ID 			INTEGER 	PRIMARY KEY	AUTOINCREMENT," +
	    			" USER_ID		INTEGER, " + 
	    			" PROGRAM_ID	INTEGER	NOT NULL, " +
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
	
	private void insertTestUser() {
		UserDao dao = new UserDao();
		if (dao.findById("test") == null) {
			User user = new User();
	    	user.setName("testperson");
	    	user.setId("test");
	    	user.setPassword("test");
	    	
	    	dao.insert(user);
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
    
    protected int executeInsertQuery(String sql, Dao.ParameterSetter parameters, Dao.IdSetter idSetter) {
        Connection connection = null;
        int resultId = -1;
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
                resultId = key.getInt(1);
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
        return resultId;
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
