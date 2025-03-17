package jjgg.academysystem.repositories;

import jjgg.academysystem.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = {"userrol","userrol.rol"})
    User findByDocument(Long document);

    @EntityGraph(attributePaths = {"userrol","userrol.rol"})
    User findByUsername(String username);
}
