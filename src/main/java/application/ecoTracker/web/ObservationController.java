package application.ecoTracker.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

    @Value("${imageFolder}")
    private String imageFolder;

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

    @RequestMapping("/observation/{id}/upload")
    @ResponseBody
    public void uploadImage(@PathVariable long id, @RequestParam("image") MultipartFile image){
        try {
            File path = new File(imageFolder + id + "/");
            image.transferTo(new File(imageFolder + id + "/" + path.list().length + ".png")); 
        } catch (Exception e) {
            LOGGER.info("Can't upload for observation " + id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/observation/create")
    @ResponseBody
    public ObservationData create(@RequestPart("observationDTO") ObservationDTO observationDTO, @RequestPart("image") MultipartFile image){

        User author = userDAO.findByPseudo(observationDTO.getAuthor_pseudo());
        if(author == null) {
            LOGGER.info("User " + observationDTO.getAuthor_pseudo() + " not found");
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

        if(!author.getCompaignList().contains(compaign)){
            LOGGER.info("User " + author.getPseudo() + " not registered in " + compaign.getId());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        
        Observation observation = new Observation(author, compaign, observationDTO.getTaxonomyGroup(), observationDTO.getTitle(), observationDTO.getLocation(), observationDTO.getDescription());
        observationDAO.save(observation);

        File pathFile = new File(imageFolder + observation.getId());
        pathFile.mkdir();

        try {
            image.transferTo(new File(imageFolder + observation.getId() + "/0" + ".png"));
        } catch (Exception e) {
            LOGGER.warning("Error creating Observation " + observation);
            e.printStackTrace();
            observationDAO.delete(observation);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } 



        return new ObservationData(observation);
    }

    
    
}
