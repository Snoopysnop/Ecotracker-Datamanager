package application.ecoTracker.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Comment implements Serializable {

    private long id;
    private LocalDateTime creationDate;
    private String content;

    private User author;
    private Observation observation;
    private Comment reference;

    protected Comment(){

    }

    public Comment(String content, User author, Observation observation, Comment reference) {
        this.creationDate = LocalDateTime.now();
        this.content = content;
        this.author = author;
        this.observation = observation;
        this.reference = reference;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    @OneToOne
    public Comment getReference() {
        return reference;
    }

    public void setReference(Comment reference) {
        this.reference = reference;
    }
     
}
