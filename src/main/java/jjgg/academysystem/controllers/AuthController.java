package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.UserRol;
import jjgg.academysystem.exceptions.UserNotFoundException;
import jjgg.academysystem.security.AuthResponse;
import jjgg.academysystem.security.LoginRequest;
import jjgg.academysystem.services.AuthService;
import jjgg.academysystem.services.Implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

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
        user.setPhoto("url-front");
        Set<UserRol> userRols = new HashSet<>();

        Rol rol = new Rol(2L, "user", null);

        UserRol userRol = new UserRol();
        userRol.setUser(user);
        userRol.setRol(rol);

        userRols.add(userRol);

        return userServiceImpl.saveUser(user, userRols);
    }
}
