package application.ecoTracker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;
    

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @RequestMapping("/create")
    @ResponseBody
    public User create(@RequestBody String pseudo){
        User user = new User(pseudo);
        userDAO.save(user);
        return user;
    }





}
