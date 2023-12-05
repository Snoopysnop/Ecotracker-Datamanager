package application.ecoTracker.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import application.ecoTracker.domain.comment.Comment;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User implements Serializable {

    private String pseudo;
    
    private List<Observation> observationList;
    private List<Campaign> campaignList = new ArrayList<>();
    private List<Comment> commentList;

    protected User() {
        
    }


    public User(String pseudo) {
        this.pseudo = pseudo;
    }

    @Id
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @OneToMany(mappedBy  = "author")
    public List<Observation> getObservationList() {
        return observationList;
    }

    public void setObservationList(List<Observation> observationList) {
        this.observationList = observationList;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    @OneToMany(mappedBy = "author")
    public List<Comment> getCommentList() {
        return commentList;
    }


    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
