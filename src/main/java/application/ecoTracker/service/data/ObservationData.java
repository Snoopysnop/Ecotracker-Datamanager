package application.ecoTracker.service.data;

import java.time.LocalDateTime;
import java.util.List;

import application.ecoTracker.domain.Observation;

public class ObservationData {

    private String auteurNickname;

    private String taxonomyGroup;
    private String title;
    private List<String> imageList;
    private String location;
    private String description;
    private LocalDateTime creationDate;


    public ObservationData(Observation observation){
        this.auteurNickname = observation.getAuteur().getNickname();
        this.taxonomyGroup = observation.getTaxonomyGroup().name();
        this.title = observation.getTitle();
        this.imageList = observation.getImageList();
        this.location = observation.getLocation().toString();
        this.description = observation.getDescription();
        this.creationDate = observation.getCreationDate();

    }

    public String getAuteurNickname() {
        return auteurNickname;
    }
    public void setAuteurNickname(String auteurNickname) {
        this.auteurNickname = auteurNickname;
    }
    public String getTaxonomyGroup() {
        return taxonomyGroup;
    }
    public void setTaxonomyGroup(String taxonomyGroup) {
        this.taxonomyGroup = taxonomyGroup;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<String> getImageList() {
        return imageList;
    }
    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    

    

    
}
