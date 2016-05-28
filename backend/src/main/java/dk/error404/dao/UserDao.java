
package dk.error404.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.error404.model.User;

public class UserDao
    extends Dao<User>
{


    public UserDao() {
        super();
    }

    public User create(ResultSet resultSet)
        throws SQLException
    {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setPassword(resultSet.getString("password"));
        String name = resultSet.getString("name");
        if (resultSet.wasNull()) {
            name = null;
        }
        user.setName(name);
        String email = resultSet.getString("email");
        if (resultSet.wasNull()) {
            email = null;
        }
        user.setEmail(email);
        user.setUserType(resultSet.getString("user_type"));
        String school = resultSet.getString("school");
        if (resultSet.wasNull()) {
            school = null;
        }
        user.setSchool(school);
        return user;
    }

    public ArrayList<User> findAll() {
        return executeQuery("SELECT * FROM user", null);
    }

    public ArrayList<User> findAll(int start, int count, String order) {
        return executeQuery(("SELECT * FROM user ORDER BY " + order+ " LIMIT " + start + ",  " + count+ " " ), null);
    }

    public void insert(final User user) {
        executeInsert("INSERT INTO `user` (`id`, `password`, `name`, `email`, `user_type`, `school`) VALUES (?, ?, ?, ?, ?, ?)", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, user.getId());
                statement.setString(2, user.getPassword());
                if (user.getName()!= null) {
                    statement.setString(3, user.getName());
                } else {
                    statement.setNull(3, 12);
                }
                if (user.getEmail()!= null) {
                    statement.setString(4, user.getEmail());
                } else {
                    statement.setNull(4, 12);
                }
                if (user.getUserType() != null) {
                	statement.setString(5, user.getUserType());
                } else {
                	statement.setString(5, User.DEFAULT_USER_TYPE);
                }
                if (user.getSchool()!= null) {
                    statement.setString(6, user.getSchool());
                } else {
                    statement.setNull(6, 12);
                }
            }

        });
    }

    public void update(final User user) {
        executeUpdateDelete("UPDATE `user` SET `password` = ?, `name` = ?, `email` = ?, `user_type` = ?, `school` = ? WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, user.getPassword());
                if (user.getName()!= null) {
                    statement.setString(2, user.getName());
                } else {
                    statement.setNull(2, 12);
                }
                if (user.getEmail()!= null) {
                    statement.setString(3, user.getEmail());
                } else {
                    statement.setNull(3, 12);
                }
                if (user.getUserType() != null) {
                	statement.setString(5, user.getUserType());
                } else {
                	statement.setString(5, User.DEFAULT_USER_TYPE);
                }
                if (user.getSchool()!= null) {
                    statement.setString(5, user.getSchool());
                } else {
                    statement.setNull(5, 12);
                }
                statement.setObject(6, user.getId());
            }

        }
        );
    }

    public void delete(final User user) {
        executeUpdateDelete("DELETE FROM `user` WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, user.getId());
            }

        }
        );
    }

    public User findById(final String id) {
        ArrayList<User> result = executeQuery("SELECT * FROM user WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, id);
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
