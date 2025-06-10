package jjgg.academysystem.services;

import jjgg.academysystem.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface UserService {

    Set<User> getallusers();

    User getUser(Long id);

    User saveUser(User user) throws Exception;

    User updateUser(User user) throws Exception;

    void deleteUser(Long id);

    User getUserByUsername(String username);
}
