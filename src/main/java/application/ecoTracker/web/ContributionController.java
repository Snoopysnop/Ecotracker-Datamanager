package application.ecoTracker.web;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.DAO.ContributionDAO;
import application.ecoTracker.domain.Contribution;
import application.ecoTracker.domain.User;
import application.ecoTracker.domain.utils.TaxonomyGroup;
import application.ecoTracker.service.DTO.ContributionDTO;
import application.ecoTracker.service.data.ContributionData;

@RestController
@RequestMapping("/contribution")
public class ContributionController {

    private static final Logger LOGGER = Logger.getLogger(ContributionController.class.getName());

    @Autowired
    private ContributionDAO contributionDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Contribution> findAll() {
        return contributionDAO.findAll();
    }

    @RequestMapping("/create")
    @ResponseBody
    public ContributionData create(@RequestBody ContributionDTO contributionDTO){

        User auteur;

        try{
            auteur = userDAO.findById(contributionDTO.getAuteur_id()).get();
        }
        catch (Exception e) {
            LOGGER.warning("auteur with id " + contributionDTO.getAuteur_id() + " not found");
            LOGGER.warning(e.toString());
            return null;
        }
        
        Contribution contribution =  new Contribution(auteur, contributionDTO.getTaxonomyGroup(), contributionDTO.getTitle(), contributionDTO.getImageFile(), contributionDTO.getLocation(), contributionDTO.getDescription());
        contributionDAO.save(contribution);
        return new ContributionData(contribution);
    }
    
    
}
