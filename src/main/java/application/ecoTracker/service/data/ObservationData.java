package application.ecoTracker.service.data;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

import application.ecoTracker.domain.Observation;

public class ObservationData implements Serializable {

    private Long id;

    private String author;

    private String taxonomyGroup;
    private String title;
    private String[] imageList;
    private String location;
    private String description;
    private LocalDateTime creationDate;

    @Value("${imageFolder}")
    private String imageFolder;


    public ObservationData(Observation observation){
        this.id = observation.getId();
        this.author = observation.getAuthor().getPseudo();
        this.taxonomyGroup = observation.getTaxonomyGroup().name();
        this.title = observation.getTitle();
        this.imageList = new File("/home/mfouillen/cours/MMM/ecoTracker/datamanager/images/" + id + "/").list(); // TODO use imageFolder
        this.location = observation.getLocation().toString();
        this.description = observation.getDescription();
        this.creationDate = observation.getCreationDate();

    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
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
    public String[] getImageList() {
        return imageList;
    }
    public void setImageList(String[] imageList) {
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



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    

    

    
}
