package application.ecoTracker.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.ecoTracker.DAO.CampaignDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.domain.Campaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.User;
import application.ecoTracker.service.data.ObservationData;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping()
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Value("${observationsImageFolder}")
    private String observationsImageFolder;
    
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private CampaignDAO campaignDAO;
    

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Returns all users"
    )
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Create an user"
    )
    public User create(@RequestBody String pseudo){

        if(userDAO.findByPseudo(pseudo) == null){
            User user = new User(pseudo);
            userDAO.save(user);
            return user;
        }

        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/user/{pseudo}/observations", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Returns all observations made by user"
    )
    public List<ObservationData> findUserObservations(@PathVariable String pseudo){
        List<Observation> observationList = observationDAO.findByUserPseudo(pseudo);

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

    @RequestMapping(value = "/user/{pseudo}/add", method = RequestMethod.PUT)
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Add campaign to user's favorite list"
    )
    public void registerToCampaign(@PathVariable String pseudo, @RequestBody String campaign_id){

        User user = userDAO.findByPseudo(pseudo);
        if(user == null) {
            LOGGER.info("User " + pseudo + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        

        Campaign campaign;
        try {
            campaign = campaignDAO.findById(Long.parseLong(campaign_id)).get();
        } 
        catch(Exception e){
            LOGGER.info("Campaign " + campaign_id + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        List<Campaign> userCampaigns = user.getCampaignList();
        userCampaigns.add(campaign);
        user.setCampaignList(userCampaigns);

        userDAO.save(user);
    }




}
