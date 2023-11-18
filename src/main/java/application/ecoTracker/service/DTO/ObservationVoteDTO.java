package application.ecoTracker.service.DTO;

import java.io.Serializable;

import application.ecoTracker.domain.utils.Vote;

public class ObservationVoteDTO implements Serializable {

    private String user_pseudo;
    private long observation_id;
    private Vote vote;

    public String getUser_pseudo() {
        return user_pseudo;
    }
    public void setUser_pseudo(String user_pseudo) {
        this.user_pseudo = user_pseudo;
    }
    public long getObservation_id() {
        return observation_id;
    }
    public void setObservation_id(long observation_id) {
        this.observation_id = observation_id;
    }
    public Vote getVote() {
        return vote;
    }
    public void setVote(Vote vote) {
        this.vote = vote;
    } 
    
    
}
