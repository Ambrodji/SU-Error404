
package dk.error404.model;

import java.io.Serializable;

public class QuestionType
    implements Serializable
{

    private int id;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return description;
    }

    public void setText(String text) {
        this.description = text;
    }

}
