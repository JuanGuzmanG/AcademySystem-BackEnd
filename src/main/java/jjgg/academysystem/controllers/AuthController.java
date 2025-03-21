package jjgg.academysystem.controllers;

import jjgg.academysystem.security.AuthResponse;
import jjgg.academysystem.security.LoginRequest;
import jjgg.academysystem.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
