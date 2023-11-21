package application.ecoTracker.service.data;

import java.io.Serializable;

import application.ecoTracker.domain.Comment;

public class CommentData implements Serializable {

    private long id;

    private String creationDate;
    private String content;

    private String author;
    private long observation_id;
    private long reference_id;

    protected CommentData(){

    }

    public CommentData(long id, String creationDate, String content, String author, long observation_id,
            long reference_id) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
        this.author = author;
        this.observation_id = observation_id;
        this.reference_id = reference_id;
    }

    public CommentData(Comment comment) {
        this.id = comment.getId();
        this.creationDate = comment.getCreationDate().toString();
        this.content = comment.getContent();
        this.author = comment.getAuthor().getPseudo();
        this.observation_id = comment.getObservation().getId();
        if(comment.getReference() == null) {
            this.reference_id = 0;
        }
        else {
            this.reference_id = comment.getReference().getId();
        }
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getObservation_id() {
        return observation_id;
    }

    public void setObservation_id(long observation_id) {
        this.observation_id = observation_id;
    }

    public long getReference_id() {
        return reference_id;
    }

    public void setReference_id(long reference_id) {
        this.reference_id = reference_id;
    }
    
}
