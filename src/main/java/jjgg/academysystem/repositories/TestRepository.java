package jjgg.academysystem.repositories;

import jjgg.academysystem.entities.Subject;
import jjgg.academysystem.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{
    List<Test> findBySubject(Subject subject);
    
    List<Test> findByActive(boolean state);

    List<Test> findBySubjectAndActive(Subject subject, boolean state);
}
