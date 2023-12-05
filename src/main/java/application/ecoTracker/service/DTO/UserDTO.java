package application.ecoTracker.service.DTO;

import application.ecoTracker.domain.User;

public class UserDTO {
    String pseudo;

    protected UserDTO(){
    } 
    
    public UserDTO(User user){
        this.pseudo = user.getPseudo();
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    
}
