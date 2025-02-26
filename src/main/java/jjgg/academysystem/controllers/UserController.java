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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) throws Exception{
        Set<UserRol> userRols = new HashSet<>();

        Rol rol = new Rol();
        rol.setIdRol(2L);
        rol.setNameRol("profesor");

        UserRol userRol = new UserRol();
        userRol.setUser(user);
        userRol.setRol(rol);

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
