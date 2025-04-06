package jjgg.academysystem.repositories;

import jjgg.academysystem.entities.Question;
import jjgg.academysystem.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByTest(Test test);


}
