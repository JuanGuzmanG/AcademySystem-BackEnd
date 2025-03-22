package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.services.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/get_user_{id}")
    public User getUser(@PathVariable Long id){
        return userServiceImpl.getUser(id);
    }

    @DeleteMapping("/delete_{id}")
    public void deleteUser(@PathVariable Long id){
        userServiceImpl.deleteUser(id);
    }

    @GetMapping("/userlogged")
    public User getLoggedUser(@AuthenticationPrincipal UserDetails userDetails) {
        return this.userServiceImpl.getUserByUsername(userDetails.getUsername());
    }
}
