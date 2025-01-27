package application.ecoTracker.DAO;

import org.springframework.transaction.annotation.Transactional;

import application.ecoTracker.domain.Campaign;
import application.ecoTracker.domain.Observation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Transactional
public interface ObservationDAO extends JpaRepository<Observation, Long> {

    public List<Observation> findByCampaignId(Long id);

    @Query("Select o from Observation o where o.author.pseudo = ?1")
    public List<Observation> findByUserPseudo(String pseudo);

    @Query("Select o.campaign from Observation o where o.author.pseudo = ?1")
    public List<Campaign> findCampaignsByUserPseudo(String pseudo);

    
}
