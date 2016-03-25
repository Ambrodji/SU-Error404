package dk.error404.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dk.error404.model.User;

public class UserDao extends Dao<User> {

	@Override
	public User create(ResultSet result) throws SQLException {
		/* 
		 * Dependency dependency = new Dependency();
        dependency.setLegacyStr(resultSet.getString("legacy_str"));
        dependency.setGroupId(resultSet.getString("group_id"));
        dependency.setArtifactId(resultSet.getString("artifact_id"));
        dependency.setVersion(resultSet.getString("version"));
        return dependency;
		 */
		return null;
	}

}
