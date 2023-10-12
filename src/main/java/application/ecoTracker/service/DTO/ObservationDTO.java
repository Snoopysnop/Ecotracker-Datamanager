package application.ecoTracker.service.DTO;

import java.io.Serializable;

import application.ecoTracker.domain.utils.GPSCoordinates;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class ObservationDTO implements Serializable {

    private String author_pseudo;
    private long campaign_id;

    private TaxonomyGroup taxonomyGroup;
    private String title;
    private GPSCoordinates location;
    private String description;

    protected ObservationDTO() {

    }

    public String getAuthor_pseudo() {
        return author_pseudo;
    }

    public void setAuthor_pseudo(String author_pseudo) {
        this.author_pseudo = author_pseudo;
    }

    public TaxonomyGroup getTaxonomyGroup() {
        return taxonomyGroup;
    }

    public void setTaxonomyGroup(TaxonomyGroup taxonomyGroup) {
        this.taxonomyGroup = taxonomyGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GPSCoordinates getLocation() {
        return location;
    }

    public void setLocation(GPSCoordinates location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(long campaign_id) {
        this.campaign_id = campaign_id;
    }

}
