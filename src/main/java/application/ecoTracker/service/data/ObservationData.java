package application.ecoTracker.service.data;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.utils.GPSCoordinates;

public class ObservationData implements Serializable {

    private Long id;

    private String author;

    private String taxonomyGroup;
    private String title;
    private List<byte[]> imageList;
    private GPSCoordinates location;
    private String description;
    private LocalDateTime creationDate;

    public ObservationData(Observation observation, String observationsImageFolder) throws IOException{
        this.id = observation.getId();
        this.author = observation.getAuthor().getPseudo();
        this.taxonomyGroup = observation.getTaxonomyGroup().name();
        this.title = observation.getTitle();

        this.imageList = new ArrayList<>();
        String[]  imageFiles = new File(observationsImageFolder + id + "/").list();
        for(String imageFile : imageFiles){
            File image = new File(observationsImageFolder + this.id + "/" + imageFile);
            imageList.add(Files.readAllBytes(image.toPath()));
        }

        this.location = observation.getLocation();
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
    public List<byte[]> getImageList() {
        return imageList;
    }
    public void setImageList(List<byte[]> imageList) {
        this.imageList = imageList;
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
