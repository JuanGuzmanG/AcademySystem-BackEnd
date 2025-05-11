package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.exceptions.UserNotFoundException;
import jjgg.academysystem.security.AuthResponse;
import jjgg.academysystem.security.LoginRequest;
import jjgg.academysystem.services.AuthService;
import jjgg.academysystem.services.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody LoginRequest loginRequest) throws Exception {
        try{
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(new AuthResponse("User not found"));
        }
    }

    @PostMapping("/new_user")
    public User saveUser(@RequestBody User user) throws Exception{
        return userServiceImpl.saveUser(user);
    }
}
