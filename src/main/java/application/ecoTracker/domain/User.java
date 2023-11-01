package application.ecoTracker.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User implements Serializable {

    private String pseudo;
    private LocalDateTime creationDate;

    private List<Observation> observationList;
    private List<Campaign> campaignList = new ArrayList<>();
    private List<ObservationVote> observationVoteList;

    protected User() {
        
    }


    public User(String pseudo) {
        this.pseudo = pseudo;
        this.creationDate = LocalDateTime.now();
    }

    @Id
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @OneToMany(mappedBy  = "author")
    public List<Observation> getObservationList() {
        return observationList;
    }

    public void setObservationList(List<Observation> observationList) {
        this.observationList = observationList;
    }

    @ManyToMany()
    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    @OneToMany(mappedBy  = "user")
    public List<ObservationVote> getObservationVoteList() {
        return observationVoteList;
    }


    public void setObservationVoteList(List<ObservationVote> observationVoteList) {
        this.observationVoteList = observationVoteList;
    }

}
