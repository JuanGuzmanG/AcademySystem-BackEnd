package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get_user_{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("all_users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getallusers());
    }

    @GetMapping("/userlogged")
    public User getLoggedUser(@AuthenticationPrincipal UserDetails userDetails) {
        return this.userService.getUserByUsername(userDetails.getUsername());
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
