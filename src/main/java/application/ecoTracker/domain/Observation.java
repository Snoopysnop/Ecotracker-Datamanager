package application.ecoTracker.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import application.ecoTracker.domain.utils.GPSCoordinates;
import application.ecoTracker.domain.utils.TaxonomyGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Observation implements Serializable {

    private long id;

    private User author;
    private Campaign campaign ;

    private TaxonomyGroup taxonomyGroup;
    private String title;
    private GPSCoordinates location;
    private String description;
    private LocalDateTime creationDate;

    private List<ObservationVote> votes;

    protected Observation() {

    }

    public Observation(User author, Campaign campaign, TaxonomyGroup taxonomyGroup, String title,
            GPSCoordinates location, String description) {
        this.author = author;
        this.campaign = campaign;
        this.taxonomyGroup = taxonomyGroup;
        this.title = title;
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
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
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

    @OneToMany(mappedBy = "observation")
    public List<ObservationVote> getVotes() {
        return votes;
    }

    public void setVotes(List<ObservationVote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Observation [id=" + id + ", author=" + author + ", taxonomyGroup=" + taxonomyGroup + ", title=" + title
                + ", location=" + location + ", description=" + description
                + ", creationDate=" + creationDate + "]";
    }


}
