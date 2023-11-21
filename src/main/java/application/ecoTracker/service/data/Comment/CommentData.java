package application.ecoTracker.service.data.Comment;

import java.io.Serializable;

import application.ecoTracker.domain.comment.Comment;

public class CommentData implements Serializable {

    private String creationDate;
    private String content;
    private String author;

    protected CommentData(){

    }

    public CommentData(Comment comment) {
        this.creationDate = comment.getCreationDate().toString();
        this.content = comment.getContent();
        this.author = comment.getAuthor().getPseudo();
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
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
