package application.ecoTracker.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User implements Serializable{

    private long id;
    private String nickname;
    private LocalDateTime creationDate;

    private List<Contribution> contributionList = new ArrayList<>();

    protected User() {
        
    }


    public User(String nickname) {
        this.nickname = nickname;
        this.creationDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @OneToMany(mappedBy  = "auteur")
    public List<Contribution> getContributionList() {
        return contributionList;
    }

    public void setContributionList(List<Contribution> contributionList) {
        this.contributionList = contributionList;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", nickname=" + nickname + ", creationDate=" + creationDate + ", contributionList="
                + contributionList + "]";
    }


    
    

}
