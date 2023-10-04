package application.ecoTracker.service.data;

import java.time.LocalDateTime;

import application.ecoTracker.domain.Contribution;

public class ContributionData {

    private String auteurNickname;

    private String taxonomyGroup;
    private String title;
    private String imageFile;
    private String location;
    private String description;
    private LocalDateTime creationDate;


    public ContributionData(Contribution contribution){
        this.auteurNickname = contribution.getAuteur().getNickname();
        this.taxonomyGroup = contribution.getTaxonomyGroup().name();
        this.title = contribution.getTitle();
        this.imageFile = contribution.getImageFile();
        this.location = contribution.getLocation().toString();
        this.description = contribution.getDescription();
        this.creationDate = contribution.getCreationDate();

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
    public String getImageFile() {
        return imageFile;
    }
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
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
