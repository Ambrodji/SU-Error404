
package dk.error404.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.error404.model.Program;

public class ProgramDao
    extends Dao<Program>
{

    public ProgramDao() {
        super();
    }

    public Program create(ResultSet resultSet)
        throws SQLException
    {
        Program program = new Program();
        program.setId(resultSet.getInt("id"));
        program.setName(resultSet.getString("name"));
        String description = resultSet.getString("description");
        if (resultSet.wasNull()) {
            description = null;
        }
        program.setDescription(description);
        program.setFileName(resultSet.getString("file_name"));
        program.setUploadedBy(resultSet.getString("uploaded_by"));
        program.setDifficulties(resultSet.getInt("difficulties"));
        return program;
    }

    public ArrayList<Program> findAll() {
        return executeQuery("SELECT * FROM program", null);
    }

    public ArrayList<Program> findAll(int start, int count, String order) {
        return executeQuery(("SELECT * FROM program ORDER BY " + order+ " LIMIT " + start + ",  " + count+ " " ), null);
    }

    public void insert(final Program program) {
        executeInsert("INSERT INTO `program` (`id`, `name`, `description`, `file_name`, `uploaded_by`, `difficulties`) VALUES (?, ?, ?, ?, ?, ?)", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(2, program.getName());
                if (program.getDescription()!= null) {
                    statement.setString(3, program.getDescription());
                } else {
                    statement.setNull(3, 12);
                }
                statement.setString(4, program.getFileName());
                statement.setString(5, program.getUploadedBy());
                statement.setInt(6, program.getDifficulties());
            }

        }, new Dao.IdSetter() {

            @Override
            public void setId(int id) {
                program.setId(id);
            }

        });
    }

    public void update(final Program program) {
        executeUpdateDelete("UPDATE `program` SET `name` = ?, `description` = ?, `file_name` = ?, `uploaded_by` = ?, `difficulties` = ? WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, program.getName());
                if (program.getDescription()!= null) {
                    statement.setString(2, program.getDescription());
                } else {
                    statement.setNull(2, 12);
                }
                statement.setString(3, program.getFileName());
                statement.setString(4, program.getUploadedBy());
                statement.setInt(5, program.getDifficulties());
                statement.setObject(6, program.getId());
            }

        }
        );
    }

    public void delete(final Program program) {
        executeUpdateDelete("DELETE FROM `program` WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, program.getId());
            }

        }
        );
    }

    public Program findById(final Integer id) {
        ArrayList<Program> result = executeQuery("SELECT * FROM program WHERE `id` = ?", new Dao.ParameterSetter() {


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
