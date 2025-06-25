package jjgg.academysystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jjgg.academysystem.DTO.ChangePasswordDTO;
import jjgg.academysystem.DTO.UserCreateDTO;
import jjgg.academysystem.DTO.UserResponseDTO;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.exceptions.UserNotFoundException;
import jjgg.academysystem.repositories.UserRepository;
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
    public ResponseEntity<UserResponseDTO> saveUser(@RequestParam("user") String userJson,
                                                    @RequestParam(value = "file", required = false) MultipartFile multipartFile
                                                    ) throws Exception{

        UserCreateDTO userCreateDTO = objectMapper.readValue(userJson, UserCreateDTO.class);

        UserResponseDTO savedUser = userService.saveUser(userCreateDTO, multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        authService.changeUserPassword(
                username,
                changePasswordDTO.getCurrentPassword(),
                changePasswordDTO.getNewPassword()
        );

        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));

    }
}
