
package dk.error404.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionAPIDto {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("questionId")
    @Expose
    private int questionId;
    @SerializedName("choices")
    @Expose
    private List<String> choices = new ArrayList<String>();

    /**
     * 
     * @return
     *     The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 
     * @param question
     *     The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 
     * @return
     *     The questionId
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * 
     * @param questionId
     *     The question ID
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    /**
     * 
     * @return
     *     The choices
     */
    public List<String> getChoices() {
        return choices;
    }

    /**
     * 
     * @param choices
     *     The choices
     */
    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

}
