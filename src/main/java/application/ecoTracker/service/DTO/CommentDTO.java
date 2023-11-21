package application.ecoTracker.service.DTO;

import java.io.Serializable;

public class CommentDTO implements Serializable {

    private String content;

    private String author;
    private long observation_id;
    private long reference_id;

    protected CommentDTO() {

    }

    public CommentDTO(String content, String author, long observation_id, long reference_id) {
        this.content = content;
        this.author = author;
        this.observation_id = observation_id;
        this.reference_id = reference_id;
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
