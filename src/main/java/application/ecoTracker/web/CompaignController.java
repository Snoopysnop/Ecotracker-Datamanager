package application.ecoTracker.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.ecoTracker.DAO.CompaignDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.DAO.OrganizationDAO;
import application.ecoTracker.domain.Compaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.Organization;
import application.ecoTracker.service.DTO.CompaignDTO;
import application.ecoTracker.service.data.CompaignData;
import application.ecoTracker.service.data.ObservationData;



@RestController
public class CompaignController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Value("${imageFolder}")
    private String imageFolder;

    @Autowired
    private CompaignDAO compaignDAO;

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private OrganizationDAO organizationDAO;

    @RequestMapping("/compaign/{id}")
    @ResponseBody
    public CompaignData findById(@PathVariable long id){

        Compaign compaign;
        try{
            compaign =  compaignDAO.findById(id).get();
        }

        catch (Exception exception) {
            LOGGER.info("Compaign " + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
       return new CompaignData(compaign);
    }

    @RequestMapping("/compaigns")
    @ResponseBody
    public List<CompaignData> findAll(){
        List<Compaign> compaignList = compaignDAO.findAll();

        List<CompaignData> compaignDataList = new ArrayList<>();
        for(Compaign compaign : compaignList) {
            try{
                compaignDataList.add(new CompaignData(compaign));
            }
            catch(Exception e){
                LOGGER.warning("error getting compaign " + compaign.getId());
                LOGGER.warning(e.toString());
            }
            
        }

        return compaignDataList;
    }

    
    @RequestMapping("/compaign/create")
    @ResponseBody
    public CompaignDTO create(@RequestBody CompaignDTO compaignDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        Organization organization;

        try{
            organization = organizationDAO.findById(compaignDTO.getOrganization_id()).get();
        }

        catch (Exception e) {
            LOGGER.info("organization " + compaignDTO.getOrganization_id() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Compaign compaign = new Compaign(compaignDTO.getName(), compaignDTO.getDescription(), LocalDateTime.parse(compaignDTO.getStartDate(), formatter), LocalDateTime.parse(compaignDTO.getEndDate(), formatter), compaignDTO.getGroupsToIdentify(), compaignDTO.getArea(), organization);
        compaignDAO.save(compaign);
        return compaignDTO;

    }

    @RequestMapping("/compaign/{id}/observations")
    @ResponseBody
    public List<ObservationData> findObservationsWithId(@PathVariable long id){
        List<Observation> observationList = observationDAO.findByCompaignId(id);

        List<ObservationData> observationDataList = new ArrayList<>();
        for(Observation observation : observationList){
            observationDataList.add(new ObservationData(observation, imageFolder));
        }

        return observationDataList;

    }


    
}
