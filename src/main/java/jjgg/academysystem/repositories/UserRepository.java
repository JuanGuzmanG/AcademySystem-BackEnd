package jjgg.academysystem.repositories;

import jjgg.academysystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByDocument(Long document);
}
