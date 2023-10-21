package application.ecoTracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.OrganizationDAO;
import application.ecoTracker.domain.Organization;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationDAO organizationDAO;

    @RequestMapping(value = "/organization/create", method = RequestMethod.POST)
    @ResponseBody
    @Operation(
        tags = {"Organization"},
        description = "Create an organization"
    )
    public Organization create(@RequestBody String name){
        return organizationDAO.save(new Organization(name));

    }
    
}
