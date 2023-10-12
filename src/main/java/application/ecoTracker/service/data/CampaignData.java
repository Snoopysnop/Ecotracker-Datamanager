package application.ecoTracker.service.data;

import java.io.Serializable;
import java.util.List;

import application.ecoTracker.domain.Campaign;
import application.ecoTracker.domain.utils.Area;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class CampaignData implements Serializable {
    
    private long id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private List<TaxonomyGroup> groupsToIdentify;
    private Area area;

    private String organization_name;

    public CampaignData(Campaign campaign) {
        this.id = campaign.getId();
        this.name = campaign.getName();
        this.description = campaign.getDescription();
        this.startDate = campaign.getStartDate().toString();
        this.endDate = campaign.getEndDate().toString();
        this.groupsToIdentify = campaign.getGroupsToIdentify();
        this.area = campaign.getArea();
        this.organization_name = campaign.getOrganization().getName();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public String getOrganization_name() {
        return organization_name;
    }
    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }
    

}
