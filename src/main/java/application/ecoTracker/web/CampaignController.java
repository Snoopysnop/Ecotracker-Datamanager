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

import application.ecoTracker.DAO.CampaignDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.DAO.OrganizationDAO;
import application.ecoTracker.domain.Campaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.Organization;
import application.ecoTracker.service.DTO.CampaignDTO;
import application.ecoTracker.service.data.CampaignData;
import application.ecoTracker.service.data.ObservationData;



@RestController
public class CampaignController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Value("${imageFolder}")
    private String imageFolder;

    @Autowired
    private CampaignDAO campaignDAO;

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private OrganizationDAO organizationDAO;

    @RequestMapping("/campaign/{id}")
    @ResponseBody
    public CampaignData findById(@PathVariable long id){

        Campaign campaign;
        try{
            campaign =  campaignDAO.findById(id).get();
        }

        catch (Exception exception) {
            LOGGER.info("Campaign " + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
       return new CampaignData(campaign);
    }

    @RequestMapping("/campaigns")
    @ResponseBody
    public List<CampaignData> findAll(){
        List<Campaign> campaignList = campaignDAO.findAll();

        List<CampaignData> campaignDataList = new ArrayList<>();
        for(Campaign campaign : campaignList) {
            try{
                campaignDataList.add(new CampaignData(campaign));
            }
            catch(Exception e){
                LOGGER.warning("error getting campaign " + campaign.getId());
                LOGGER.warning(e.toString());
            }
            
        }

        return campaignDataList;
    }

    
    @RequestMapping("/campaign/create")
    @ResponseBody
    public CampaignDTO create(@RequestBody CampaignDTO campaignDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        Organization organization;

        try{
            organization = organizationDAO.findById(campaignDTO.getOrganization_id()).get();
        }

        catch (Exception e) {
            LOGGER.info("organization " + campaignDTO.getOrganization_id() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Campaign campaign = new Campaign(campaignDTO.getName(), campaignDTO.getDescription(), LocalDateTime.parse(campaignDTO.getStartDate(), formatter), LocalDateTime.parse(campaignDTO.getEndDate(), formatter), campaignDTO.getGroupsToIdentify(), campaignDTO.getArea(), organization);
        campaignDAO.save(campaign);
        return campaignDTO;

    }

    @RequestMapping("/campaign/{id}/observations")
    @ResponseBody
    public List<ObservationData> findObservationsWithId(@PathVariable long id){
        List<Observation> observationList = observationDAO.findByCampaignId(id);

        List<ObservationData> observationDataList = new ArrayList<>();
        for(Observation observation : observationList){
            observationDataList.add(new ObservationData(observation, imageFolder));
        }

        return observationDataList;

    }


    
}
