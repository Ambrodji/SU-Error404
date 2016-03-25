package dk.error404.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Dao<T> {
	private final String DATABASE_NAME = "test.db";
	
	public Dao() {
		// Initialize tabels in DB in case they don't exist already
		initializeTabels();
	}
	
	private void initializeTabels() {
		initializeUsers();
		//initializeTeams();
	}
	
	private void initializeUsers() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS USER " +
	    			"(ID 			TEXT PRIMARY KEY    NOT NULL," +
	    			" PASSWORD		TEXT	NOT NULL," +
	    			" NAME          TEXT, " + 
	    			" EMAIL			TEXT, " +
	    			" USER_TYPE		TEXT	NOT NULL," +
	    			" SCHOOL		TEXT)";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
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
