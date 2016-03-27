
package dk.error404.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.error404.model.QuestionType;

public class QuestionTypeDao
    extends Dao<QuestionType>
{

    public QuestionTypeDao() {
        super();
    }

    public QuestionType create(ResultSet resultSet)
        throws SQLException
    {
        QuestionType questionType = new QuestionType();
        questionType.setId(resultSet.getInt("id"));
        questionType.setText(resultSet.getString("description"));
        return questionType;
    }

    public ArrayList<QuestionType> findAll() {
        return executeQuery("SELECT * FROM question_type", null);
    }

    public ArrayList<QuestionType> findAll(int start, int count, String order) {
        return executeQuery(("SELECT * FROM question_type ORDER BY " + order+ " LIMIT " + start + ",  " + count+ " " ), null);
    }

    public void insert(final QuestionType questionType) {
        executeInsert("INSERT INTO `question_type` (`id`, `description`) VALUES (?, ?)", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, questionType.getId());
                statement.setString(2, questionType.getText());
            }

        }, new Dao.IdSetter() {

            @Override
            public void setId(int id) {
                questionType.setId(id);
            }

        });
    }

    public void update(final QuestionType questionType) {
        executeUpdateDelete("UPDATE `question_type` SET `description` = ? WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, questionType.getText());
                statement.setObject(2, questionType.getId());
            }

        }
        );
    }

    public void delete(final QuestionType questionType) {
        executeUpdateDelete("DELETE FROM `question_type` WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, questionType.getId());
            }

        }
        );
    }

    public QuestionType findById(final Integer id) {
        ArrayList<QuestionType> result = executeQuery("SELECT * FROM question_type WHERE `id` = ?", new Dao.ParameterSetter() {


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
