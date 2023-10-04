package application.ecoTracker.DAO;

import org.springframework.transaction.annotation.Transactional;

import application.ecoTracker.domain.Observation;

import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface ObservationDAO extends JpaRepository<Observation, Long> {

    
}
