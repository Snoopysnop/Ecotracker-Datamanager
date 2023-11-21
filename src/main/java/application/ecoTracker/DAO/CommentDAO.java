package application.ecoTracker.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.ecoTracker.domain.Comment;
import jakarta.transaction.Transactional;

@Transactional
public interface CommentDAO extends JpaRepository<Comment, Long> {

    @Query("Select c from Comment c where c.observation.id = ?1")
    public List<Comment> findByObservationId(Long id);
    
}
