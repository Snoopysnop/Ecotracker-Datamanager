package application.ecoTracker.domain.comment;

import java.io.Serializable;
import java.time.LocalDateTime;

import application.ecoTracker.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public abstract class Comment implements Serializable {

    private long id;
    private LocalDateTime creationDate;
    private String content;

    private User author;
    
    protected Comment() {

    }

    public Comment(String content, User author) {
        this.creationDate = LocalDateTime.now();
        this.content = content;
        this.author = author;
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

}
