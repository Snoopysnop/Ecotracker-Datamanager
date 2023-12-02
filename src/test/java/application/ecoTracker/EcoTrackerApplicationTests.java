package application.ecoTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.ecoTracker.DAO.CampaignDAO;
import application.ecoTracker.DAO.CommentDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.DAO.OrganizationDAO;
import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.service.DTO.CampaignDTO;
import application.ecoTracker.service.DTO.ObservationDTO;
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

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private ObservationDAO observationDAO;

	@Autowired
	private OrganizationDAO organizationDAO;


	@Test
	void contextLoads() {
	}

	void createObservationsData() throws IOException {
		File observations_file = new File("src/test/resources/exampleData/observations.json");
		String observations_string = "";
		Scanner sc = new Scanner(observations_file);

		while (sc.hasNextLine()) {
			observations_string += sc.nextLine();
		  }
		  sc.close();

		ObjectMapper objectMapper = new ObjectMapper();
		List<ObservationDTO> observations = objectMapper.readValue(observations_string, new TypeReference<List<ObservationDTO>>(){});

		for(ObservationDTO observation : observations){
			userController.create(observation.getAuthor_pseudo());
			MultipartFile image = new MockMultipartFile("EyedLadyBug2.jpeg", new FileInputStream(new File("src/test/ressources/EyedLadyBug1.jpeg")));
			observationController.create(observation, image);
		}

	}

	void createCampaignsData() throws IOException {
		File campaigns_file = new File("src/test/resources/exampleData/campaings.json");
		String campaigns_string = "";
		Scanner sc = new Scanner(campaigns_file);

		while (sc.hasNextLine()) {
			campaigns_string += sc.nextLine();
		  }
		  sc.close();

		ObjectMapper objectMapper = new ObjectMapper();
		List<CampaignDTO> campaigns = objectMapper.readValue(campaigns_string, new TypeReference<List<CampaignDTO>>(){});

		for(CampaignDTO campaign : campaigns){
			MultipartFile image = new MockMultipartFile("EyedLadyBug1.jpeg", new FileInputStream(new File("src/test/ressources/EyedLadyBug1.jpeg")));
			campaignController.create(campaign, image);
		}

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
		organizationDAO.deleteAll();

		createObservationsData();
		createCampaignsData();
	}

}
