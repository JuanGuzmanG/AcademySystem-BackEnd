package jjgg.academysystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

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
    public ResponseEntity<?> updateUser(
            @RequestPart("user") String userStr,
            @RequestPart(value = "photoFile", required = false)
            MultipartFile photoFile){
        try{
            User user = objectMapper.readValue(userStr, User.class);
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Error parsing user data: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error updating user: " + e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
