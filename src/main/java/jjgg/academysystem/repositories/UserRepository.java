package jjgg.academysystem.repositories;

import jjgg.academysystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByDocument(Long document);
}
