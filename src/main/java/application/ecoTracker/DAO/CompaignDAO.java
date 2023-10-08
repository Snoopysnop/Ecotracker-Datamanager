package application.ecoTracker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import application.ecoTracker.domain.Compaign;

@Transactional
public interface CompaignDAO extends JpaRepository<Compaign, Long>{
    
}
