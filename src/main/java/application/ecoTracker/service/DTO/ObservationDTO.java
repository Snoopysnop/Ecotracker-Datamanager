package application.ecoTracker.service.DTO;

import java.io.Serializable;

import application.ecoTracker.domain.utils.GPSCoordinates;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class ObservationDTO implements Serializable {

    private String author;
    private long campaign_id;

    private TaxonomyGroup taxonomyGroup;
    private String title;
    private GPSCoordinates coordinates;
    private String description;
    private String creationDate;

    protected ObservationDTO() {

    }

    public ObservationDTO(String author, long campaign_id, TaxonomyGroup taxonomyGroup, String title,
            GPSCoordinates coordinates, String description, String creationDate) {
        this.author = author;
        this.campaign_id = campaign_id;
        this.taxonomyGroup = taxonomyGroup;
        this.title = title;
        this.coordinates = coordinates;
        this.description = description;
        this.creationDate = creationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public GPSCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GPSCoordinates coordinates) {
        this.coordinates = coordinates;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
