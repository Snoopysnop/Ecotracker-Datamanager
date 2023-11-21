package application.ecoTracker.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.ecoTracker.domain.comment.Comment;
import application.ecoTracker.domain.comment.MainComment;
import jakarta.transaction.Transactional;

@Transactional
public interface CommentDAO extends JpaRepository<Comment, Long> {

    @Query("Select mc from MainComment mc where mc.observation.id = ?1")
    public List<MainComment> findMainCommentByObservationId(Long id);

    @Query("Select mc from MainComment mc where mc.id = ?1")
    public Comment findMainCommentById(Long id);
    
}
