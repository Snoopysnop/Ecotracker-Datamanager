package application.ecoTracker.DAO;

import org.springframework.transaction.annotation.Transactional;

import application.ecoTracker.domain.Contribution;

import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface ContributionDAO extends JpaRepository<Contribution, Long> {

    
}
