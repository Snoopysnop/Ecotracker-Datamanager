package application.ecoTracker.web;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
import io.swagger.v3.oas.annotations.Operation;



@RestController
public class CampaignController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Value("${observationsImageFolder}")
    private String observationsImageFolder;

    @Value("${campaignsImageFolder}")
    private String campaignsImageFolder;

    @Autowired
    private CampaignDAO campaignDAO;

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private OrganizationDAO organizationDAO;

    @RequestMapping(value = "/campaign/{id}", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    @Operation(
        tags = {"Campaign"},
        description = "Returns the campaign {id}"
    )
    public CampaignData findById(@PathVariable long id){

        Campaign campaign;
        try{
            campaign =  campaignDAO.findById(id).get();
        }

        catch (Exception exception) {
            LOGGER.info("Campaign " + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
       return new CampaignData(campaign, campaignsImageFolder);
    }

    @RequestMapping(value = "/campaigns", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    @Operation(
        tags = {"Campaign"},
        description = "Returns all campaigns"
    )
    public List<CampaignData> findAll(){
        List<Campaign> campaignList = campaignDAO.findAll();

        List<CampaignData> campaignDataList = new ArrayList<>();
        for(Campaign campaign : campaignList) {
            try{
                campaignDataList.add(new CampaignData(campaign, campaignsImageFolder));
            }
            catch(Exception e){
                LOGGER.warning("error getting campaign " + campaign.getId());
                LOGGER.warning(e.toString());
            }
            
        }

        return campaignDataList;
    }

    
    @RequestMapping(value = "/campaign/create", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    @Operation(
        tags = {"Campaign"},
        description = "Create a campaign"
    )
    public CampaignData create(@RequestPart("campaign") CampaignDTO campaignDTO, @RequestPart("image") MultipartFile image) {

        // TODO : is user admin
        // TODO : handle who can create with wich organizations ?

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        Organization organization = organizationDAO.findByName(campaignDTO.getAuthor());

        if(organization == null) {
            LOGGER.info("organization " + campaignDTO.getAuthor() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Campaign campaign = new Campaign(campaignDTO.getTitle(), campaignDTO.getDescription(), LocalDateTime.parse(campaignDTO.getStartDate(), formatter), LocalDateTime.parse(campaignDTO.getEndDate(), formatter), campaignDTO.getGroupsToIdentify(), campaignDTO.getArea(), organization);
        campaignDAO.save(campaign);

        // save image
        File pathFile = new File(campaignsImageFolder + campaign.getId());
        pathFile.mkdir();

        try {
            image.transferTo(new File(campaignsImageFolder + campaign.getId() + "/image" + ".png"));
        } catch (Exception e) {
            LOGGER.warning("Error creating campaign " + campaign);
            e.printStackTrace();
            campaignDAO.delete(campaign);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new CampaignData(campaign, campaignsImageFolder);

    }

    @RequestMapping(value = "/campaign/{id}/observations", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    @Operation(
        tags = {"Campaign"},
        description = "Returns all observations for the campaign {id}"
    )
    public List<ObservationData> findObservationsWithId(@PathVariable long id){
        List<Observation> observationList = observationDAO.findByCampaignId(id);

        List<ObservationData> observationDataList = new ArrayList<>();
        for(Observation observation : observationList){
            try {
                observationDataList.add(new ObservationData(observation, observationsImageFolder));
            } catch (IOException e) {
                LOGGER.warning("Error getting observations for campaign " + id);
                e.printStackTrace();
            }
        }

        return observationDataList;

    }


    
}
