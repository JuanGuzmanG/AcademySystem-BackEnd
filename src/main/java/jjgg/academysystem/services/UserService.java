package jjgg.academysystem.services;

import jjgg.academysystem.entities.User;

import java.util.Set;

public interface UserService {

    Set<User> getallusers();

    User getUser(Long id);

    User saveUser(User user) throws Exception;

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserByUsername(String username);
}
