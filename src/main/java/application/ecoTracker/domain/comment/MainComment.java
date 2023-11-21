package application.ecoTracker.domain.comment;

import java.util.ArrayList;
import java.util.List;

import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class MainComment extends Comment {

    private Observation observation;
    private List<Reply> replyList = new ArrayList<>();

    protected MainComment() {
        super();
    }

    public MainComment(String content, User author, Observation observation) {
        super(content, author);
        this.observation = observation;
    }



    @ManyToOne
    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    @OneToMany(mappedBy = "reference")
    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    

    
}
