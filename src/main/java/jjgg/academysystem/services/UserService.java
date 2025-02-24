package jjgg.academysystem.services;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.userRol;

import java.util.Set;

public interface UserService {
    User saveUser(User user, Set<userRol> userrol) throws Exception;
}
