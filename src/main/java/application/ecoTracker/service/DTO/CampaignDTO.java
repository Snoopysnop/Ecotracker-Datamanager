package application.ecoTracker.service.DTO;

import java.io.Serializable;
import java.util.List;

import application.ecoTracker.domain.utils.Area;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class CampaignDTO implements Serializable {

    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private List<TaxonomyGroup> groupsToIdentify;
    private Area area;

    private String organization_name;

    protected CampaignDTO() {

    }

    public CampaignDTO(String title, String description, String startDate, String endDate,
            List<TaxonomyGroup> groupsToIdentify, Area area, String organization_name) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupsToIdentify = groupsToIdentify;
        this.area = area;
        this.organization_name = organization_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    
    
}
