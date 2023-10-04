package application.ecoTracker.web;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.DAO.ObservationDAO;
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

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Observation> findAll() {
        return observationDAO.findAll();
    }

    @RequestMapping("/create")
    @ResponseBody
    public ObservationData create(@RequestBody ObservationDTO observationDTO){

        User auteur;

        try{
            auteur = userDAO.findById(observationDTO.getAuteur_id()).get();
        }
        catch (Exception e) {
            LOGGER.warning("auteur with id " + observationDTO.getAuteur_id() + " not found");
            LOGGER.warning(e.toString());
            return null;
        }
        
        Observation observation = new Observation(auteur, observationDTO.getTaxonomyGroup(), observationDTO.getTitle(), observationDTO.getImageList(), observationDTO.getLocation(), observationDTO.getDescription());
        observationDAO.save(observation);
        return new ObservationData(observation);
    }
    
    
}
