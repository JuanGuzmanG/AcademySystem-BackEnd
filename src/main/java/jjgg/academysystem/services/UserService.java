package jjgg.academysystem.services;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.UserRol;

import java.util.Set;

public interface UserService {
    User saveUser(User user) throws Exception;

    Set<User> getallusers();

    User getUser(Long id);

    void deleteUser(Long id);

    User getUserByUsername(String username);
}
