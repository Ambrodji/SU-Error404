
package dk.error404.model;

import java.io.Serializable;

public class Program
    implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private String description;
    private String fileName;
    private String uploadedBy;
    private int difficulties;

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

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public int getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(int difficulties) {
        this.difficulties = difficulties;
    }

}
