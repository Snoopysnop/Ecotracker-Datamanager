package application.ecoTracker.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.ecoTracker.domain.utils.Area;
import application.ecoTracker.domain.utils.TaxonomyGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Campaign implements Serializable {

    private Long id;
    
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<TaxonomyGroup> groupsToIdentify;
    private Area area;
    private String imageLocation;

    private List<Observation> observationList;
    private List<User> userList;
    private Organization organization;

    protected Campaign() {

    }

    public Campaign(String title, String description, LocalDateTime startDate, LocalDateTime endDate,
            List<TaxonomyGroup> groupsToIdentify, Area area, Organization organization) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupsToIdentify = groupsToIdentify;
        this.observationList = new ArrayList<>();
        this.area = area;
        this.organization = organization;
    }



    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<TaxonomyGroup> getGroupsToIdentify() {
        return groupsToIdentify;
    }

    public void setGroupsToIdentify(List<TaxonomyGroup> groupsToIdentify) {
        this.groupsToIdentify = groupsToIdentify;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }


    @OneToMany(mappedBy  = "campaign")
    public List<Observation> getObservationList() {
        return observationList;
    }

    public void setObservationList(List<Observation> observationList) {
        this.observationList = observationList;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @ManyToOne
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    
    
}
