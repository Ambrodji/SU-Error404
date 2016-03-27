
package dk.error404.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.error404.model.TeamParticipant;

public class TeamParticipantDao
    extends Dao<TeamParticipant>
{


    public TeamParticipantDao() {
        super();
    }

    public TeamParticipant create(ResultSet resultSet)
        throws SQLException
    {
        TeamParticipant teamParticipant = new TeamParticipant();
        teamParticipant.setUserId(resultSet.getString("user_id"));
        teamParticipant.setTeamId(resultSet.getInt("team_id"));
        teamParticipant.setRole(resultSet.getString("role"));
        return teamParticipant;
    }

    public ArrayList<TeamParticipant> findAll() {
        return executeQuery("SELECT * FROM team_participant", null);
    }

    public ArrayList<TeamParticipant> findAll(int start, int count, String order) {
        return executeQuery(("SELECT * FROM team_participant ORDER BY " + order+ " LIMIT " + start + ",  " + count+ " " ), null);
    }

    public void insert(final TeamParticipant teamParticipant) {
        executeInsert("INSERT INTO `team_participant` (`user_id`, `team_id`, `role`) VALUES (?, ?, ?)", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, teamParticipant.getUserId());
                statement.setInt(2, teamParticipant.getTeamId());
                statement.setString(3, teamParticipant.getRole());
            }

        });
    }

    public void update(final TeamParticipant teamParticipant) {
        executeUpdateDelete("UPDATE `team_participant` SET `role` = ? WHERE `team_id` = ? AND `user_id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, teamParticipant.getRole());
                statement.setObject(2, teamParticipant.getTeamId());
                statement.setObject(3, teamParticipant.getUserId());
            }

        }
        );
    }

    public void delete(final TeamParticipant teamParticipant) {
        executeUpdateDelete("DELETE FROM `team_participant` WHERE `team_id` = ? AND `user_id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, teamParticipant.getTeamId());
                statement.setString(2, teamParticipant.getUserId());
            }

        }
        );
    }

    public TeamParticipant findByTeam_idAndUser_id(final Integer teamId, final String userId) {
        ArrayList<TeamParticipant> result = executeQuery("SELECT * FROM team_participant WHERE `team_id` = ? AND `user_id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, teamId);
                statement.setString(2, userId);
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
