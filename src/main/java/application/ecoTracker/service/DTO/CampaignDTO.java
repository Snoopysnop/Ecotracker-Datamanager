package application.ecoTracker.service.DTO;

import java.io.Serializable;
import java.util.List;

import application.ecoTracker.domain.utils.Area;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class CampaignDTO implements Serializable {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private List<TaxonomyGroup> groupsToIdentify;
    private Area area;

    private long organization_id;

    protected CampaignDTO() {

    }

    public CampaignDTO(String name, String description, String startDate, String endDate,
            List<TaxonomyGroup> groupsToIdentify, Area area, long organization_id) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupsToIdentify = groupsToIdentify;
        this.area = area;
        this.organization_id = organization_id;
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

    public long getOrganization_id() {
        return organization_id;
    }


    public void setOrganization_id(long organization_id) {
        this.organization_id = organization_id;
    }

    
    
}
