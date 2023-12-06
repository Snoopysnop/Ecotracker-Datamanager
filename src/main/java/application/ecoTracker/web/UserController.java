package application.ecoTracker.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.domain.Campaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.User;
import application.ecoTracker.service.DTO.UserDTO;
import application.ecoTracker.service.data.CampaignData;
import application.ecoTracker.service.data.ObservationData;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping()
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());
    
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ObservationDAO observationDAO;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Returns all users"
    )
    public List<UserDTO> findAll() {
        
        List<UserDTO> users = new ArrayList<>();
        for(User user : userDAO.findAll()){
            users.add(new UserDTO(user));
        }

        return users;
    }

    @RequestMapping(value = "/user/{pseudo}", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Returns the user"
    )
    public UserDTO findOne(@PathVariable String pseudo) {
        
        User user = userDAO.findByPseudo(pseudo);
        if(user == null) {
            LOGGER.info("User " + pseudo + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new UserDTO(user);
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    @CrossOrigin
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
    @CrossOrigin
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Returns all observations made by user"
    )
    public List<ObservationData> findUserObservations(@PathVariable String pseudo){
        List<Observation> observationList = observationDAO.findByUserPseudo(pseudo);

        List<ObservationData> observationDataList = new ArrayList<>();
        for(Observation observation : observationList){
            observationDataList.add(new ObservationData(observation));
        }        

        return observationDataList;

        

    }

    @RequestMapping(value = "/user/{pseudo}/campaigns", method = RequestMethod.GET)
    @CrossOrigin
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

        List<CampaignData> campaignDatas = new ArrayList<>();
        for(Campaign campaign : observationDAO.findCampaignsByUserPseudo(pseudo)){
            campaignDatas.add(new CampaignData(campaign));
        }

        return campaignDatas;

        
    }

    @RequestMapping(value = "/user/{pseudo}/upload", method = RequestMethod.PATCH)
    @CrossOrigin
    @ResponseBody
    @Operation(
        tags = {"User"},
        description = "Set user image"
    )
    public void uploadImage(@PathVariable String pseudo, @RequestParam("image") MultipartFile image){

        // TODO : check is user logged in
        User user = userDAO.findByPseudo(pseudo);
        if(user == null) {
            LOGGER.info("User " + pseudo + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try {
            user.setImage(image.getBytes());
            userDAO.save(user);
        } catch (Exception e) {
            LOGGER.info("Can't upload profile picture for user " + pseudo);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    



}
