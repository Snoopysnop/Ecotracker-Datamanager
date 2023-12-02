package application.ecoTracker.DAO;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import application.ecoTracker.domain.Organization;

@Transactional
public interface OrganizationDAO extends JpaRepository<Organization, Long> {

}
