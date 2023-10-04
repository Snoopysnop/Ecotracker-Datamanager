package application.ecoTracker.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import application.ecoTracker.domain.utils.GPSCoordinates;
import application.ecoTracker.domain.utils.TaxonomyGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contribution implements Serializable {

    private long id;

    private User auteur;

    private TaxonomyGroup taxonomyGroup;
    private String title;
    private String imageFile;
    private GPSCoordinates location;
    private String description;
    private LocalDateTime creationDate;

    private int upVoteCount = 0;
    private int downVoteCount = 0;

    protected Contribution() {

    }


    public Contribution(User auteur, TaxonomyGroup taxonomyGroup, String title, String imageFile,
            GPSCoordinates location, String description) {
        this.auteur = auteur;
        this.taxonomyGroup = taxonomyGroup;
        this.title = title;
        this.imageFile = imageFile;
        this.location = location;
        this.description = description;
        this.creationDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    public User getAuteur() {
        return auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    public TaxonomyGroup getTaxonomyGroup() {
        return this.taxonomyGroup;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(int downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    @Override
    public String toString() {
        return "Contribution [id=" + id + ", auteur=" + auteur + ", taxonomyGroup=" + taxonomyGroup + ", title=" + title
                + ", imageFile=" + imageFile + ", location=" + location + ", description=" + description
                + ", creationDate=" + creationDate + ", upVoteCount=" + upVoteCount + ", downVoteCount=" + downVoteCount
                + "]";
    }

    

}
