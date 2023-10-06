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

import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.DAO.CompaignDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.domain.Compaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.User;
import application.ecoTracker.service.DTO.ObservationDTO;
import application.ecoTracker.service.data.ObservationData;

@RestController
public class ObservationController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CompaignDAO compaignDAO;

    @RequestMapping("/observation/{id}")
    @ResponseBody
    public ObservationData findById(@PathVariable long id){

        Observation observation;
        try {
            observation = observationDAO.findById(id).get();
        }

        catch (Exception exception) {
            LOGGER.info("Observation " + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ObservationData(observation);
    }

    @RequestMapping("/observations")
    @ResponseBody
    public List<ObservationData> findAll() {
        List<Observation> observationList = observationDAO.findAll();

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

    @RequestMapping("observation/create")
    @ResponseBody
    public ObservationData create(@RequestBody ObservationDTO observationDTO){

        User author;

        try{
            author = userDAO.findById(observationDTO.getAuthor_id()).get();
        }
        catch (Exception e) {
            LOGGER.info("User " + observationDTO.getAuthor_id() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Compaign compaign;

        try{
            compaign = compaignDAO.findById(observationDTO.getCompaign_id()).get();
            LOGGER.info(compaign.toString());
        }
        catch (Exception e) {
            LOGGER.info("Compaign " + observationDTO.getCompaign_id() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Observation observation = new Observation(author, compaign, observationDTO.getTaxonomyGroup(), observationDTO.getTitle(), observationDTO.getImageList(), observationDTO.getLocation(), observationDTO.getDescription());
        observationDAO.save(observation);
        return new ObservationData(observation);
    }

    
    
}
