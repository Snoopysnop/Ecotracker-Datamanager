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
import application.ecoTracker.DAO.CommentDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.domain.User;
import application.ecoTracker.domain.comment.Comment;
import application.ecoTracker.domain.utils.Area;
import application.ecoTracker.domain.utils.GPSCoordinates;
import application.ecoTracker.domain.utils.TaxonomyGroup;
import application.ecoTracker.service.DTO.CampaignDTO;
import application.ecoTracker.service.DTO.ObservationDTO;
import application.ecoTracker.service.DTO.comment.CommentDTO;
import application.ecoTracker.service.data.CampaignData;
import application.ecoTracker.service.data.ObservationData;
import application.ecoTracker.web.CampaignController;
import application.ecoTracker.web.ObservationController;
import application.ecoTracker.web.UserController;


@SpringBootTest
class EcoTrackerApplicationTests {

	@Autowired
    private UserController userController;

	@Autowired
    private CampaignController campaignController;

	@Autowired
	private ObservationController observationController;

	@Autowired
	private CampaignDAO campaignDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private ObservationDAO observationDAO;
	


	@Test
	void contextLoads() {
	}

	/**
	 * Clear database and create test data
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	void initDatabaseWithTestData() throws FileNotFoundException, IOException {

		// clear database
		commentDAO.deleteAll();
		observationDAO.deleteAll();
		userDAO.deleteAll();
		campaignDAO.deleteAll();

		User snoopy = userController.create("Snoopy");
		User frageli = userController.create("Frageli");

		String mairie_de_rennes = "Mairie de Rennes";

		List<TaxonomyGroup> groupsToIdentify = Arrays.asList(TaxonomyGroup.Insects);
		Area area = new Area(new GPSCoordinates(48.130195, -1.650862), 2000);

		MultipartFile image = new MockMultipartFile("EyedLadyBug1.jpeg", new FileInputStream(new File("src/test/ressources/EyedLadyBug1.jpeg")));
		CampaignDTO campaignDTO = new CampaignDTO("Lady Bug", "Campaign to find Lady Bugs", "2023-10-04 12:00:00", "2023-10-11 12:00:00", groupsToIdentify, area, mairie_de_rennes);

		CampaignData campaign = campaignController.create(campaignDTO, image);
		
		ObservationDTO observationDTO = new ObservationDTO(snoopy.getPseudo(), campaign.getId(), TaxonomyGroup.Insects, "Lady Bug Observation", new GPSCoordinates(0, 0), "Lady Bug Observation description");
	    image = new MockMultipartFile("EyedLadyBug2.jpeg", new FileInputStream(new File("src/test/ressources/EyedLadyBug2.jpeg")));


		ObservationData observationData = observationController.create(observationDTO, image);

		CommentDTO commentDTO = new CommentDTO("comment", frageli.getPseudo());
		observationController.comment(observationData.getId(), commentDTO);

		Comment reference = commentDAO.findAll().get(0);

		commentDTO = new CommentDTO("reply", snoopy.getPseudo());
		observationController.reply(reference.getId(), commentDTO);
	}

}
