package jjgg.academysystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.services.StorageService;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StorageService storageService;

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
            @RequestParam("user") String userJson,
            @RequestParam(value = "file", required = false) MultipartFile multipartFile){
        try{
            User user = objectMapper.readValue(userJson, User.class);
            System.out.println(user);
            User currentUser = userService.getUser(user.getDocument());

            if(multipartFile != null && !multipartFile.isEmpty()) {
                String oldPhotoUrl = currentUser.getPhoto();
                if(oldPhotoUrl!=null && !oldPhotoUrl.isEmpty()) {
                    if(!oldPhotoUrl.equals("http://localhost:8080/media/default.jpg")) {
                        String oldmultiparFilename = oldPhotoUrl.substring(oldPhotoUrl.lastIndexOf('/')+1);
                        storageService.delete(oldmultiparFilename);
                    }
                }
                String newMultipartFilename = storageService.store(multipartFile);
                String newPhotoUrl = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/media/")
                        .path(newMultipartFilename)
                        .toUriString();
                currentUser.setPhoto(newPhotoUrl);
            }
            currentUser.setDocumentType(user.getDocumentType());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setMiddleName(user.getMiddleName());
            currentUser.setLastName(user.getLastName());
            currentUser.setSecondLastName(user.getSecondLastName());
            currentUser.setEmail(user.getEmail());
            currentUser.setPhoneNumber(user.getPhoneNumber());
            currentUser.setCountryBirth(user.getCountryBirth());
            currentUser.setBirthDate(user.getBirthDate());
            currentUser.setGender(user.getGender());
            currentUser.setBloodType(user.getBloodType());
            currentUser.setRols(user.getRols());

            User updatedUser = userService.updateUser(currentUser);
            System.out.println("actualizado"+ updatedUser);
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
