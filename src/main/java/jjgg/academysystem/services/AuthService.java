package jjgg.academysystem.services;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.repositories.UserRepository;
import jjgg.academysystem.security.AuthResponse;
import jjgg.academysystem.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthResponse(token);
    }

    public void changeUserPassword(String username, String currentPassword, String newPassword){
        User user = userRepository.findByUsername(username).orElseThrow();

        if(!passwordEncoder.matches(currentPassword,user.getPassword())){
            throw new IllegalArgumentException("Wrong password");
        }

        if(passwordEncoder.matches(newPassword,user.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }
}
