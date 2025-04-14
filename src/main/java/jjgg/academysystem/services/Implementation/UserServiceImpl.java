package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.repositories.RolRepository;
import jjgg.academysystem.repositories.UserRepository;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User saveUser(User user) throws Exception {
        User localuser = userRepository.findById(user.getDocument()).orElse(null);

        if (localuser != null) {
            throw new Exception("User already exists");
        }
        user.setPhoto("url-front");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(String.valueOf(user.getDocument()));

        Set<Rol> roles = new HashSet<>();
        Rol rol = rolRepository.findById(2L).orElse(null);
        roles.add(rol);
        user.setRols(roles);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}


