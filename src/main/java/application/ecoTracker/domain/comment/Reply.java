package application.ecoTracker.domain.comment;

import application.ecoTracker.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Reply extends Comment {

    private Comment reference;
    
    protected Reply() {
        super();
    }

    public Reply(String content, User author, Comment reference) {
        super(content, author);
        this.reference = reference;
    }

    @ManyToOne
    public Comment getReference() {
        return reference;
    }

    public void setReference(Comment reference) {
        this.reference = reference;
    }

    
    
}
