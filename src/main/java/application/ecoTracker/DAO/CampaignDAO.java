package application.ecoTracker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import application.ecoTracker.domain.Campaign;

@Transactional
public interface CampaignDAO extends JpaRepository<Campaign, Long>{
    
}
