
package dk.error404.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.error404.model.UserType;

public class UserTypeDao
    extends Dao<UserType>
{

    public UserTypeDao() {
        super();
    }

    public UserType create(ResultSet resultSet)
        throws SQLException
    {
        UserType userType = new UserType();
        userType.setId(resultSet.getInt("id"));
        userType.setDescription(resultSet.getString("description"));
        return userType;
    }

    public ArrayList<UserType> findAll() {
        return executeQuery("SELECT * FROM user_type", null);
    }

    public ArrayList<UserType> findAll(int start, int count, String order) {
        return executeQuery(("SELECT * FROM user_type ORDER BY " + order+ " LIMIT " + start + ",  " + count+ " " ), null);
    }

    public void insert(final UserType userType) {
        executeInsert("INSERT INTO `user_type` (`id`, `description`) VALUES (?, ?)", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, userType.getId());
                statement.setString(2, userType.getDescription());
            }

        }, new Dao.IdSetter() {

            @Override
            public void setId(int id) {
                userType.setId(id);
            }

        });
    }

    public void update(final UserType userType) {
        executeUpdateDelete("UPDATE `user_type` SET `description` = ? WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, userType.getDescription());
                statement.setObject(2, userType.getId());
            }

        }
        );
    }

    public void delete(final UserType userType) {
        executeUpdateDelete("DELETE FROM `user_type` WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, userType.getId());
            }

        }
        );
    }

    public UserType findById(final Integer id) {
        ArrayList<UserType> result = executeQuery("SELECT * FROM user_type WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, id);
            }

        }
        );
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

}
