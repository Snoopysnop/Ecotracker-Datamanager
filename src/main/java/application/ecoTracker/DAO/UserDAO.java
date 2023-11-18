package application.ecoTracker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import application.ecoTracker.domain.User;
import jakarta.transaction.Transactional;


@Transactional
public interface UserDAO extends JpaRepository<User, String> {

    public User findByPseudo(String pseudo);
    
}
