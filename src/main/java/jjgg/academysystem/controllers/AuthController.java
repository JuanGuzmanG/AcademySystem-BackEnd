package jjgg.academysystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jjgg.academysystem.DTO.ChangePasswordDTO;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.exceptions.UserNotFoundException;
import jjgg.academysystem.security.AuthResponse;
import jjgg.academysystem.security.LoginRequest;
import jjgg.academysystem.services.AuthService;
import jjgg.academysystem.services.Implementation.UserServiceImpl;
import jjgg.academysystem.services.StorageService;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        try{
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(new AuthResponse(e.getMessage()));
        }
    }

    @PostMapping("/new_user")
    public User saveUser(@RequestParam("user") String userJson,
                         @RequestParam(value = "file", required = false) MultipartFile multipartFile,
                         HttpServletRequest request) throws Exception{

        User user = objectMapper.readValue(userJson, User.class);

       if(multipartFile != null && !multipartFile.isEmpty()) {
           String path = storageService.store(multipartFile);
           String url = ServletUriComponentsBuilder
                   .fromCurrentContextPath()
                   .path("/media/")
                   .path(path)
                   .toUriString();
           user.setPhoto(url);
       } else {
           String defaultURL = ServletUriComponentsBuilder
                   .fromCurrentContextPath()
                   .path("/media/")
                   .path("default.jpg")
                   .toUriString();
           user.setPhoto(defaultURL);
       }

        return userService.saveUser(user);
    }

    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO){
        try{
            System.out.println("prueba de ingreso");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth);
            if(auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
            String username = auth.getName();
            System.out.println("Username: " + username);
            authService.changeUserPassword(username, changePasswordDTO.getCurrentPassword(), changePasswordDTO.getNewPassword());
            return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
        } catch (Exception e){
            return ResponseEntity.status(500).body(Map.of("message", "An error occurred while changing the password"));
        }
    }
}
