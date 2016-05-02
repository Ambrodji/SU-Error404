
package dk.error404.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.error404.model.Question;

public class QuestionDao
    extends Dao<Question>
{

    public QuestionDao() {
        super();
    }

    public Question create(ResultSet resultSet)
        throws SQLException
    {
        Question question = new Question();
        question.setId(resultSet.getInt("id"));
        question.setUserId(resultSet.getString("user_id"));
        question.setProgramId(resultSet.getInt("program_id"));
        question.setQuestionText(resultSet.getString("question_text"));
        String questionOptions = resultSet.getString("question_options");
        if (resultSet.wasNull()) {
            questionOptions = null;
        }
        question.setQuestionOptions(questionOptions);
        String answer = resultSet.getString("answer");
        if (resultSet.wasNull()) {
            answer = null;
        }
        question.setAnswer(answer);
        String feedback = resultSet.getString("feedback");
        if (resultSet.wasNull()) {
            feedback = null;
        }
        question.setFeedback(feedback);
        String tsCompleted = resultSet.getString("ts_completed");
        if (resultSet.wasNull()) {
            tsCompleted = null;
        }
        question.setTsCompleted(tsCompleted);
        return question;
    }

    public ArrayList<Question> findAll() {
        return executeQuery("SELECT * FROM question", null);
    }

    public ArrayList<Question> findAll(int start, int count, String order) {
        return executeQuery(("SELECT * FROM question ORDER BY " + order+ " LIMIT " + start + ",  " + count+ " " ), null);
    }

    public int insert(final Question question) {
        return executeInsertQuery("INSERT INTO `question` (`id`, `user_id`, `program_id`, `question_text`, `question_options`, `answer`, `feedback`, `ts_completed`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                //statement.setInt(1, question.getId());
                statement.setString(2, question.getUserId());
                statement.setInt(3, question.getProgramId());
                statement.setString(4, question.getQuestionText());
                if (question.getQuestionOptions()!= null) {
                    statement.setString(5, question.getQuestionOptions());
                } else {
                    statement.setNull(5, 12);
                }
                if (question.getAnswer()!= null) {
                    statement.setString(6, question.getAnswer());
                } else {
                    statement.setNull(6, 12);
                }
                if (question.getFeedback()!= null) {
                    statement.setString(7, question.getFeedback());
                } else {
                    statement.setNull(7, 12);
                }
                if (question.getTsCompleted()!= null) {
                    statement.setString(8, question.getTsCompleted());
                } else {
                    statement.setNull(8, 12);
                }
            }

        }, new Dao.IdSetter() {

            @Override
            public void setId(int id) {
                question.setId(id);
            }

        });
    }

    public void update(final Question question) {
        executeUpdateDelete("UPDATE `question` SET `user_id` = ?, `program_id` = ?, `question_text` = ?, `question_options` = ?, `answer` = ?, `feedback` = ?, `ts_completed` = ? WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setString(1, question.getUserId());
                statement.setInt(2, question.getProgramId());
                statement.setString(3, question.getQuestionText());
                if (question.getQuestionOptions()!= null) {
                    statement.setString(4, question.getQuestionOptions());
                } else {
                    statement.setNull(4, 12);
                }
                if (question.getAnswer()!= null) {
                    statement.setString(5, question.getAnswer());
                } else {
                    statement.setNull(5, 12);
                }
                if (question.getFeedback()!= null) {
                    statement.setString(6, question.getFeedback());
                } else {
                    statement.setNull(6, 12);
                }
                if (question.getTsCompleted()!= null) {
                    statement.setString(7, question.getTsCompleted());
                } else {
                    statement.setNull(7, 12);
                }
                statement.setObject(8, question.getId());
            }

        }
        );
    }

    public void delete(final Question question) {
        executeUpdateDelete("DELETE FROM `question` WHERE `id` = ?", new Dao.ParameterSetter() {


            @Override
            public void setParameters(PreparedStatement statement)
                throws SQLException
            {
                statement.setInt(1, question.getId());
            }

        }
        );
    }

    public Question findById(final Integer id) {
        ArrayList<Question> result = executeQuery("SELECT * FROM question WHERE `id` = ?", new Dao.ParameterSetter() {


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
