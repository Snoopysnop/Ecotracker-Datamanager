package application.ecoTracker.service.DTO;

import java.io.Serializable;

import application.ecoTracker.domain.utils.GPSCoordinates;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class ObservationDTO implements Serializable {

    private String author_pseudo;
    private long compaign_id;

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

    public long getCompaign_id() {
        return compaign_id;
    }

    public void setCompaign_id(long compaign_id) {
        this.compaign_id = compaign_id;
    }

}
