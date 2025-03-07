package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.UserRol;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/new_user")
    public User saveUser(@RequestBody User user) throws Exception{
        user.setPhoto("url-front");
        Set<UserRol> userRols = new HashSet<>();

        Rol rol = new Rol(2L, "user", null);

        UserRol userRol = new UserRol();
        userRol.setUser(user);
        userRol.setRol(rol);

        userRols.add(userRol);
        return userService.saveUser(user, userRols);
    }

    @GetMapping("/get_user_{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @DeleteMapping("/delete_{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
