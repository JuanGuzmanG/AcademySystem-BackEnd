package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.userRol;
import jjgg.academysystem.repositories.RolRepository;
import jjgg.academysystem.repositories.UserRepository;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public User saveUser(User user, Set<userRol> userrol) throws Exception {
        User localuser = userRepository.findByDocument(user.getDocument());

        if (localuser != null) {
            throw new Exception("User already exists");
        } else {
            for (userRol ur : userrol) {
                rolRepository.save(ur.getRol());
            }
            user.getUserrol().addAll(userrol);
        }
        return userRepository.save(user);
    }
}


