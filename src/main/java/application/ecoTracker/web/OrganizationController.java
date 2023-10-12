package application.ecoTracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.OrganizationDAO;
import application.ecoTracker.domain.Organization;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationDAO organizationDAO;

    @RequestMapping("/organization/create")
    @ResponseBody
    public Organization create(@RequestBody String name){
        return organizationDAO.save(new Organization(name));

    }
    
}
