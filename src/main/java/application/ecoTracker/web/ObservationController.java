package application.ecoTracker.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.DAO.CampaignDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.DAO.ObservationVoteDAO;
import application.ecoTracker.domain.Campaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.ObservationVote;
import application.ecoTracker.domain.User;
import application.ecoTracker.domain.utils.Vote;
import application.ecoTracker.service.DTO.ObservationDTO;
import application.ecoTracker.service.DTO.ObservationVoteDTO;
import application.ecoTracker.service.data.ObservationData;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ObservationController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Value("${observationsImageFolder}")
    private String observationsImageFolder;

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CampaignDAO campaignDAO;

    @Autowired
    private ObservationVoteDAO observationVoteDAO;

    @RequestMapping(value = "/observation/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Returns the observation {id}"
    )
    public ObservationData findById(@PathVariable long id){

        Observation observation;
        try {
            observation = observationDAO.findById(id).get();
        }

        catch (Exception exception) {
            LOGGER.info("Observation " + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ObservationData(observation, observationsImageFolder);
    }

    @RequestMapping(value = "/observations", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Returns all observations"
    )
    public List<ObservationData> findAll() {
        List<Observation> observationList = observationDAO.findAll();

        List<ObservationData> observationDataList = new ArrayList<>();
        for(Observation observation : observationList){
            try {
                observationDataList.add(new ObservationData(observation, observationsImageFolder));
            }
            catch(Exception e){
                LOGGER.warning("error getting observation " + observation.getId());
                LOGGER.warning(e.toString());
            }
        }        

        return observationDataList;

    }

    @RequestMapping(value = "/observation/{id}/upload", method = RequestMethod.PUT)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Upload an image for the observation {id}"
    )
    public void uploadImage(@PathVariable long id, @RequestParam("image") MultipartFile image){
        try {
            File path = new File(observationsImageFolder + id + "/");
            image.transferTo(new File(observationsImageFolder + id + "/" + path.list().length + ".png")); 
        } catch (Exception e) {
            LOGGER.info("Can't upload for observation " + id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/observation/create", method = RequestMethod.POST)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Create an observation"
    )
    public ObservationData create(@RequestPart("observationDTO") ObservationDTO observationDTO, @RequestPart("image") MultipartFile image){

        User author = userDAO.findByPseudo(observationDTO.getAuthor_pseudo());
        if(author == null) {
            LOGGER.info("User " + observationDTO.getAuthor_pseudo() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Campaign campaign;

        try{
            campaign = campaignDAO.findById(observationDTO.getCampaign_id()).get();
            LOGGER.info(campaign.toString());
        }
        catch (Exception e) {
            LOGGER.info("Campaign " + observationDTO.getCampaign_id() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(!author.getCampaignList().contains(campaign)){
            LOGGER.info("User " + author.getPseudo() + " not registered in " + campaign.getId());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        
        Observation observation = new Observation(author, campaign, observationDTO.getTaxonomyGroup(), observationDTO.getTitle(), observationDTO.getLocation(), observationDTO.getDescription());
        observationDAO.save(observation);

        File pathFile = new File(observationsImageFolder + observation.getId());
        pathFile.mkdir();

        try {
            image.transferTo(new File(observationsImageFolder + observation.getId() + "/0" + ".png"));
        } catch (Exception e) {
            LOGGER.warning("Error creating Observation " + observation);
            e.printStackTrace();
            observationDAO.delete(observation);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } 



        return new ObservationData(observation, observationsImageFolder);
    }

    @RequestMapping(value = "/observation/{id}/upVotesCount", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Returns the up votes count for the observation {id}"
    )
    public int findUpVotesCountById(@PathVariable long id){
        return observationDAO.findVotesCountById(Vote.UpVote, id);
    }

    @RequestMapping(value = "/observation/{id}/downVotesCount", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Returns the down votes count for the observation {id}"
    )
    public int findDownVotesCountById(@PathVariable long id){
        return observationDAO.findVotesCountById(Vote.DownVote, id);
    }

    @RequestMapping(value = "/observation/vote", method = RequestMethod.POST)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Vote to an observation"
    )
    public void voteToObservation(@RequestBody ObservationVoteDTO observationVoteDTO){

        Observation observation;

        try{
            observation = observationDAO.findById(observationVoteDTO.getObservation_id()).get();
        }

        catch (Exception e) {
            LOGGER.info("observation " + observationVoteDTO.getObservation_id() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User author;
        try{
            author = userDAO.findByPseudo(observationVoteDTO.getUser_pseudo());
        }
        catch (Exception e) {
            LOGGER.info("user " + observationVoteDTO.getUser_pseudo() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        observationVoteDAO.delete(observationVoteDAO.findByUserAndObservationId(author, observationVoteDTO.getObservation_id()));
        ObservationVote observationVote = new ObservationVote(author, observation, observationVoteDTO.getVote());
        observationVoteDAO.save(observationVote);



    }
    
    
}
