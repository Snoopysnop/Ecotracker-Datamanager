package application.ecoTracker.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.DAO.CompaignDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.domain.Compaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.User;
import application.ecoTracker.service.DTO.ObservationDTO;
import application.ecoTracker.service.data.ObservationData;

@RestController
@RequestMapping("/observation")
public class ObservationController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CompaignDAO compaignDAO;

    @RequestMapping("/{id}")
    @ResponseBody
    public ObservationData findById(@PathVariable long id){
        Observation observation = observationDAO.findById(id).get();
        return new ObservationData(observation);
    }

    @RequestMapping("/findAll")
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

    @RequestMapping("/create")
    @ResponseBody
    public ObservationData create(@RequestBody ObservationDTO observationDTO){

        User author;

        try{
            author = userDAO.findById(observationDTO.getAuthor_id()).get();
        }
        catch (Exception e) {
            LOGGER.warning("auhtor with id " + observationDTO.getAuthor_id() + " not found");
            LOGGER.warning(e.toString());
            author =  null;
        }

        Compaign compaign;

        try{
            compaign = compaignDAO.findById(observationDTO.getCompaign_id()).get();
            LOGGER.info(compaign.toString());
        }
        catch (Exception e) {
            LOGGER.warning("compaign with id " + observationDTO.getCompaign_id() + " not found");
            LOGGER.warning(e.toString());
            compaign =  null;
        }
        
        Observation observation = new Observation(author, compaign, observationDTO.getTaxonomyGroup(), observationDTO.getTitle(), observationDTO.getImageList(), observationDTO.getLocation(), observationDTO.getDescription());
        observationDAO.save(observation);
        return new ObservationData(observation);
    }

    
    
}
