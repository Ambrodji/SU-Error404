
package dk.error404.model;

import java.io.Serializable;

public class Question
    implements Serializable
{

    private int id;
    private String userId;
    private int questionType;
    private String questionText;
    private String questionOptions;
    private String answer;
    private String feedback;
    private String tsCompleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(String questionOptions) {
        this.questionOptions = questionOptions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getTsCompleted() {
        return tsCompleted;
    }

    public void setTsCompleted(String tsCompleted) {
        this.tsCompleted = tsCompleted;
    }

}