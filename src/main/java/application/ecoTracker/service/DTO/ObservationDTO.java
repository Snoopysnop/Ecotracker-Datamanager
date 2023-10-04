package application.ecoTracker.service.DTO;

import java.io.Serializable;

import application.ecoTracker.domain.utils.GPSCoordinates;
import application.ecoTracker.domain.utils.TaxonomyGroup;

public class ObservationDTO implements Serializable {

    private long auteur_id;

    private TaxonomyGroup taxonomyGroup;
    private String title;
    private String imageFile;
    private GPSCoordinates location;
    private String description;


    protected ObservationDTO() {

    }

    public long getAuteur_id() {
        return auteur_id;
    }
    public void setAuteur_id(long auteur_id) {
        this.auteur_id = auteur_id;
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
    public String getImageFile() {
        return imageFile;
    }
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
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

    




}
