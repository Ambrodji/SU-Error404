
package dk.error404.model;

import java.io.Serializable;

public class Program
    implements Serializable
{

    private int id;
    private String name;
    private String description;
    private String fileName;
    private int uploadedBy;
    private int difficultyMin;
    private int difficultyMax;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(int uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public int getDifficultyMin() {
        return difficultyMin;
    }

    public void setDifficultyMin(int difficultyMin) {
        this.difficultyMin = difficultyMin;
    }

    public int getDifficultyMax() {
        return difficultyMax;
    }

    public void setDifficultyMax(int difficultyMax) {
        this.difficultyMax = difficultyMax;
    }

}
