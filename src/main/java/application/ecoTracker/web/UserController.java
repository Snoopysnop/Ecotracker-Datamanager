package application.ecoTracker.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import application.ecoTracker.service.data.CampaignData;
import application.ecoTracker.service.data.ObservationData;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping()
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Value("${observationsImageFolder}")
    private String observationsImageFolder;

    @Value("${campaignsImageFolder}")
    private String campaignsImageFolder;
    
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private CampaignDAO campaignDAO;
    

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Returns all users"
    )
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Create an user"
    )
    public User create(@RequestBody String pseudo){

        // TODO : handled by keycloack ?

        if(userDAO.findByPseudo(pseudo) == null){
            User user = new User(pseudo);
            userDAO.save(user);
            return user;
        }

        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/user/{pseudo}/observations", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
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

    @RequestMapping(value = "/user/{pseudo}/campaigns", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Returns all campaigns where user participated"
    )
    public List<CampaignData> findUserCampaigns(@PathVariable String pseudo){
        // TODO : check if user logged in
        // TODO : handle user in body / user logged in

        User user = userDAO.findByPseudo(pseudo);
        if(user == null) {
            LOGGER.info("User " + pseudo + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<Campaign> campaigns = campaignDAO.findAll();

        List<CampaignData> campaignDatas = new ArrayList<>();
        for(Campaign campaign : campaigns) {
            for(Observation observation : campaign.getObservationList()){
                if(observation.getAuthor().equals(user)){
                    campaignDatas.add(new CampaignData(campaign, campaignsImageFolder));
                }
            }
            
        }

        return campaignDatas;

        
    }

    



}
