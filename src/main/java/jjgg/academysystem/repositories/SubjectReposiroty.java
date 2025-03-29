package jjgg.academysystem.repositories;

import jjgg.academysystem.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectReposiroty extends JpaRepository<Subject, Long> {

}
