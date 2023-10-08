package application.ecoTracker.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.ecoTracker.DAO.CompaignDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.domain.Compaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.User;
import application.ecoTracker.service.data.ObservationData;

@RestController
@RequestMapping()
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private CompaignDAO compaignDAO;
    

    @RequestMapping("/users")
    @ResponseBody
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @RequestMapping("/user/create")
    @ResponseBody
    public User create(@RequestBody String pseudo){

        if(userDAO.findByPseudo(pseudo) == null){
            User user = new User(pseudo);
            userDAO.save(user);
            return user;
        }

        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @RequestMapping("/user/{pseudo}/observations")
    @ResponseBody
    public List<ObservationData> findUserObservations(@PathVariable String pseudo){
        List<Observation> observationList = observationDAO.findByUserPseudo(pseudo);

        List<ObservationData> observationDataList = new ArrayList<>();
        for(Observation observation : observationList){
            try {
                observationDataList.add(new ObservationData(observation));
            }
            catch(Exception e){
                LOGGER.warning("error getting observation " + observation.getId());
                LOGGER.warning(e.toString());
            }
        }        

        return observationDataList;

        

    }

    @RequestMapping("/user/{pseudo}/register")
    @ResponseBody
    public User registerToCompaign(@PathVariable String pseudo, @RequestBody String compaign_id){

        User user = userDAO.findByPseudo(pseudo);
        if(user == null) {
            LOGGER.info("User " + pseudo + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        

        Compaign compaign;
        try {
            compaign = compaignDAO.findById(Long.parseLong(compaign_id)).get();
        } 
        catch(Exception e){
            LOGGER.info("Compaign " + compaign_id + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        List<Compaign> userCompaigns = user.getCompaignList();
        userCompaigns.add(compaign);
        user.setCompaignList(userCompaigns);

        userDAO.save(user);

        return user;
    }




}
