package application.ecoTracker.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.CompaignDAO;
import application.ecoTracker.domain.Compaign;
import application.ecoTracker.service.DTO.CompaignDTO;


@RestController
@RequestMapping("/compaign")
public class CompaignController {

    @Autowired
    private CompaignDAO compaignDAO;


    @RequestMapping("/create")
    @ResponseBody
    public Compaign create(@RequestBody CompaignDTO compaignDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Compaign compaign = new Compaign(compaignDTO.getName(), compaignDTO.getDescription(), LocalDateTime.parse(compaignDTO.getStartDate(), formatter), LocalDateTime.parse(compaignDTO.getEndDate(), formatter), compaignDTO.getGroupsToIdentify(), compaignDTO.getLocation());
        compaignDAO.save(compaign);
        return compaign;

    }
    
}
