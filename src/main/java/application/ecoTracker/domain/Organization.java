package application.ecoTracker.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Organization implements Serializable {
    
    private Long id;
    private String name;

    private List<Compaign> compaignList;

    protected Organization(){

    }

    public Organization(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy  = "organization")
    public List<Compaign> getCompaignList() {
        return compaignList;
    }

    public void setCompaignList(List<Compaign> compaignList) {
        this.compaignList = compaignList;
    }

}
