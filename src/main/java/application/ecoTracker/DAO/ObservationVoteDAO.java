package application.ecoTracker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.ecoTracker.domain.ObservationVote;
import application.ecoTracker.domain.User;
import jakarta.transaction.Transactional;

@Transactional
public interface ObservationVoteDAO extends JpaRepository<ObservationVote, Long>{

    @Query("Select ov from ObservationVote ov where ov.user = ?1 and ov.observation.id = ?2")
    public ObservationVote findByUserAndObservationId(User user, Long observationId);
    
}