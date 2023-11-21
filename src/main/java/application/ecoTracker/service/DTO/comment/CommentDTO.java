package application.ecoTracker.service.DTO.comment;

import java.io.Serializable;

public abstract class CommentDTO implements Serializable {

    private String content;
    private String author;

    protected CommentDTO() {
        
    }

    public CommentDTO(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
}
