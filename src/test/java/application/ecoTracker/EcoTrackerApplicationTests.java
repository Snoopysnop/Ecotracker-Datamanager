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
import application.ecoTracker.domain.Campaign;
import application.ecoTracker.service.DTO.CampaignDTO;
import application.ecoTracker.service.DTO.ObservationDTO;
import application.ecoTracker.service.DTO.comment.CommentDTO;
import application.ecoTracker.service.data.ObservationData;
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

	private void createObservationsData() throws IOException {
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

			if(userDAO.findByPseudo(observation.getAuthor()) == null){
				userController.create(observation.getAuthor());
			}
			

			List<Campaign> campaigns = campaignDAO.findAll();
			for(Campaign campaign : campaigns) {
				if(campaign.getGroupsToIdentify().contains(observation.getTaxonomyGroup())){
					observation.setCampaign_id(campaign.getId());
					break;
				}
			}


			MultipartFile image = new MockMultipartFile("EyedLadyBug2.jpeg", new FileInputStream(new File("src/test/resources/EyedLadyBug1.jpeg")));
			observationController.create(observation, image);
		}

	}

	private void createCampaignsData() throws IOException {
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
			if(organizationDAO.findByName(campaign.getAuthor()) == null){
				organizationController.create(campaign.getAuthor());
			}
			MultipartFile image = new MockMultipartFile("EyedLadyBug1.jpeg", new FileInputStream(new File("src/test/resources/EyedLadyBug1.jpeg")));
			campaignController.create(campaign, image);
		}

	}

	private void createCommentData() {
		String author  = userDAO.findAll().get(0).getPseudo();
		String comment_content = "This is a comment.";
		String reply_content = "This is a reply";
		for(ObservationData observation : observationController.findAll()){
			// add comment
			CommentDTO comment = new CommentDTO(comment_content, author);
			observationController.comment(observation.getId(), comment);
			
			// reply to the comment
			comment = new CommentDTO(reply_content, author);
			observationController.reply(observationController.getComments(observation.getId()).get(0).getId(), comment);
			
			// add another comment
			comment = new CommentDTO(comment_content, author);
			observationController.comment(observation.getId(), comment);
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

		createCampaignsData();
		createObservationsData();
		createCommentData();
		
	}

}
