package application.ecoTracker.DAO;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.ecoTracker.domain.Organization;

@Transactional
public interface OrganizationDAO extends JpaRepository<Organization, Long> {

    @Query("Select o from Organization o where o.name = ?1")
    public Organization findByName(String name);
}
