package application.ecoTracker.service.data;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import application.ecoTracker.domain.Campaign;
import application.ecoTracker.domain.utils.Area;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class CampaignData implements Serializable {
    
    private long id;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private List<TaxonomyGroup> groupsToIdentify;
    private Area area;
    private String imageLocation;

    private String author;

    public CampaignData(Campaign campaign, String campaignsImageFolder) {
        this.id = campaign.getId();
        this.title = campaign.getTitle();
        this.description = campaign.getDescription();
        this.startDate = campaign.getStartDate().toString();
        this.endDate = campaign.getEndDate().toString();
        this.groupsToIdentify = campaign.getGroupsToIdentify();
        this.area = campaign.getArea();
        this.author = campaign.getOrganization();
        this.imageLocation = campaignsImageFolder + this.id + "/" + new File(campaignsImageFolder + this.id + "/").list()[0];
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getImageLocation() {
        return imageLocation;
    }
    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
    

}
