package jjgg.academysystem.services;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.UserRol;

import java.util.Set;

public interface UserService {
    User saveUser(User user, Set<UserRol> userRols) throws Exception;

    User getUser(Long id);

    void deleteUser(Long id);
}
