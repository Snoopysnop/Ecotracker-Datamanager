package application.ecoTracker.service.DTO;

import java.io.Serializable;
import java.util.List;

import application.ecoTracker.domain.Compaign;
import application.ecoTracker.domain.utils.Area;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class CompaignDTO implements Serializable {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private List<TaxonomyGroup> groupsToIdentify;
    private Area area;

    protected CompaignDTO() {

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


    public Area getArea() {
        return area;
    }


    public void setArea(Area area) {
        this.area = area;
    }    

    
    
}
