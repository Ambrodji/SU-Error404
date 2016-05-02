
package dk.error404.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionDbDto {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;
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
     *     The answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 
     * @param answer
     *     The answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
