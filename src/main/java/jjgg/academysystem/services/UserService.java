package jjgg.academysystem.services;

import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.userRol;

import java.util.Set;

public interface UserService {

    public User saveUser(User user, Set<userRol> userRol) throws Exception;
}
