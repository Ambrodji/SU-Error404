
package dk.error404.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.error404.model.Team;

public class TeamDao
    extends Dao<Team>
{


    public TeamDao() {
        super();
    }

    public Team create(ResultSet resultSet)
        throws SQLException
    {
        Team team = new Team();
        team.setId(resultSet.getInt("id"));
        team.setName(resultSet.getString("name"));
        String description = resultSet.getString("description");
        if (resultSet.wasNull()) {
            description = null;
        }
        team.setDescription(description);
        return team;
    }

    public ArrayList<Team> findAll() {
        return executeQuery("SELECT * FROM team", null);
    }

    public ArrayList<Team> findAll(int start, int count, String order) {
        return executeQuery(("SELECT * FROM team ORDER BY " + order+ " LIMIT " + start + ",  " + count+ " " ), null);
    }

    public void insert(final Team team) {
        executeInsert("INSERT INTO `team` (`name`, `description`) VALUES (?, ?)", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, team.getName());
                if (team.getDescription()!= null) {
                    statement.setString(2, team.getDescription());
                } else {
                    statement.setNull(2, 12);
                }
            }

        }, new Dao.IdSetter() {

            @Override
            public void setId(int id) {
                team.setId(id);
            }

        });
    } 

    public void update(final Team team) {
        executeUpdateDelete("UPDATE `team` SET `name` = ?, `description` = ? WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, team.getName());
                if (team.getDescription()!= null) {
                    statement.setString(2, team.getDescription());
                } else {
                    statement.setNull(2, 12);
                }
                statement.setObject(3, team.getId());
            }

        }
        );
    }

    public void delete(final Team team) {
        executeUpdateDelete("DELETE FROM `team` WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, team.getId());
            }

        }
        );
    }

    public Team findById(final Integer id) {
        ArrayList<Team> result = executeQuery("SELECT * FROM team WHERE `id` = ?", new Dao.ParameterSetter() {


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
