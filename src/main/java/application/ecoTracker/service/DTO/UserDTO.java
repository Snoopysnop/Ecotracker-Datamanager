package application.ecoTracker.service.DTO;

import application.ecoTracker.domain.User;

public class UserDTO {
    String pseudo;
    String creationDate;

    protected UserDTO(){
    } 

    public UserDTO(User user){
        this.pseudo = user.getPseudo();
        this.creationDate = user.getCreationDate().toString();
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    
}
