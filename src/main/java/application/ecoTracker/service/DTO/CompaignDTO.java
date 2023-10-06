package application.ecoTracker.service.DTO;

import java.io.Serializable;
import java.util.List;

import application.ecoTracker.domain.Compaign;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class CompaignDTO implements Serializable {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private List<TaxonomyGroup> groupsToIdentify;

    protected CompaignDTO() {

    }

    public CompaignDTO(Compaign compaign){
        this.name = compaign.getName();
        this.description = compaign.getDescription();
        this.startDate = compaign.getStartDate().toString();
        this.endDate = compaign.getEndDate().toString();
        this.groupsToIdentify = compaign.getGroupsToIdentify();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<TaxonomyGroup> getGroupsToIdentify() {
        return groupsToIdentify;
    }

    public void setGroupsToIdentify(List<TaxonomyGroup> groupsToIdentify) {
        this.groupsToIdentify = groupsToIdentify;
    }    
    
}
