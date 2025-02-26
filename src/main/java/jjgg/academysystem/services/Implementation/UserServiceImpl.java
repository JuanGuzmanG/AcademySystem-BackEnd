package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.UserRol;
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
    public User saveUser(User user, Set<UserRol> userrol) throws Exception {
        User localuser = userRepository.findByDocument(user.getDocument());

        if (localuser != null) {

        } else {
            for (UserRol ur : userrol) {
                rolRepository.save(ur.getRol());
            }
            user.getUserrol().addAll(userrol);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return  userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}


