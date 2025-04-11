package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.UserRol;
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
    public User saveUser(User user) throws Exception {
        User localuser = userRepository.findById(user.getDocument()).orElse(null);

        if (localuser != null) {
            throw new Exception("User already exists");
        }
        user.setPhoto("url-front");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(String.valueOf(user.getDocument()));

        userRepository.save(user);

        Rol rol = rolRepository.findById(2L).orElse(null);

        UserRol userRol = new UserRol();
        userRol.setUser(user);
        userRol.setRol(rol);

        Set<UserRol> userRols = new HashSet<>();
        userRols.add(userRol);
        User userdb = userRepository.findById(user.getDocument()).orElse(null);
        userdb.getUserrol().addAll(userRols);
        return userdb;
    }

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

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        user.getUserrol().clear();
        userRepository.delete(user);
    }
}


