package jjgg.academysystem.services.Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.repositories.RolRepository;
import jjgg.academysystem.repositories.UserRepository;
import jjgg.academysystem.services.FileSystemStorageService;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileSystemStorageService fileStorageService;

    @Override
    public Set<User> getallusers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional
    public User saveUser(User user) throws Exception {
        User localuser = userRepository.findById(user.getDocument()).orElse(null);

        if (localuser != null) {
            throw new Exception("User already exists");
        }

        //password encryption
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(String.valueOf(user.getDocument()));

        //role assignment
        Set<Rol> roles = new HashSet<>();
        Rol defaultRol = rolRepository.findById(2L).orElseThrow(() ->
                new RuntimeException("default role (user) not found"));
        roles.add(defaultRol);
        user.setRols(roles);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found"));
        userRepository.deleteById(id);
    }
}


