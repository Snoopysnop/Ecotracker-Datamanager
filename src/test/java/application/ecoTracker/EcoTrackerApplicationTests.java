package application.ecoTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import application.ecoTracker.DAO.CampaignDAO;
import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.domain.Organization;
import application.ecoTracker.domain.User;
import application.ecoTracker.domain.utils.Area;
import application.ecoTracker.domain.utils.GPSCoordinates;
import application.ecoTracker.domain.utils.TaxonomyGroup;
import application.ecoTracker.service.DTO.CampaignDTO;
import application.ecoTracker.service.DTO.ObservationDTO;
import application.ecoTracker.service.data.CampaignData;
import application.ecoTracker.web.CampaignController;
import application.ecoTracker.web.ObservationController;
import application.ecoTracker.web.OrganizationController;
import application.ecoTracker.web.UserController;


@SpringBootTest
class EcoTrackerApplicationTests {

	@Autowired
    private UserController userController;

	@Autowired
    private OrganizationController organizationController;

	@Autowired
    private CampaignController campaignController;

	@Autowired
	private ObservationController observationController;

	@Autowired
	private CampaignDAO campaignDAO;

	@Autowired
	private UserDAO userDAO;


	@Test
	void contextLoads() {
	}

	/**
	 * @require Empty database
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	void fillDatabase() throws FileNotFoundException, IOException {

		User snoopy = userController.create("Snoopy");
		User frageli = userController.create("Frageli");

		Organization mairie_de_rennes = organizationController.create("Mairie de Rennes");

		List<TaxonomyGroup> groupsToIdentify = Arrays.asList(TaxonomyGroup.Insect);
		Area area = new Area(new GPSCoordinates(0, 0), 0);

		MultipartFile image = new MockMultipartFile("EyedLadyBug1.jpeg", new FileInputStream(new File("src/test/ressources/EyedLadyBug1.jpeg")));
		CampaignDTO campaignDTO = new CampaignDTO("Lady Bug", "Campaign to find Lady Bugs", "2023-10-04 12:00:00", "2023-10-11 12:00:00", groupsToIdentify, area, mairie_de_rennes.getId());

		CampaignData campaign = campaignController.create(campaignDTO, image);

		
		userController.registerToCampaign(snoopy.getPseudo(), campaign.getId()+"");	

		ObservationDTO observationDTO = new ObservationDTO(snoopy.getPseudo(), campaign.getId(), TaxonomyGroup.Insect, "Lady Bug Observation", new GPSCoordinates(0, 0), "Lady Bug Observation description");
	    image = new MockMultipartFile("EyedLadyBug2.jpeg", new FileInputStream(new File("src/test/ressources/EyedLadyBug2.jpeg")));


		observationController.create(observationDTO, image);

	}

}
