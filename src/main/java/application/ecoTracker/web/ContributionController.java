package application.ecoTracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.ContributionDAO;
import application.ecoTracker.domain.Contribution;

@RestController
@RequestMapping("/contribution")
public class ContributionController {

    @Autowired
    private ContributionDAO contributionDAO;


    @RequestMapping("/create")
    @ResponseBody
    public Contribution create(@RequestBody Contribution contribution){
        contributionDAO.save(contribution);
        return contribution;
    }
    
    
}
