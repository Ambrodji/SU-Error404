package dk.error404.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dk.error404.model.User;

public class UserDao extends Dao<User> {
	public UserDao() {
		super();
	}

	@Override
	public User create(ResultSet result) throws SQLException {
		return null;
	}

}
