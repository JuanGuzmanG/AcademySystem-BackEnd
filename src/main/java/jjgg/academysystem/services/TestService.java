package jjgg.academysystem.services;

import jjgg.academysystem.entities.Subject;
import jjgg.academysystem.entities.Test;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface TestService {

    Test save(Test test);

    Test update(Test test);

    Set<Test> findAll();

    Test getById(Long id);

    List<Test> getAllTestsBySubject(Subject subject);

    List<Test> getAllTestsByActive();

    List<Test> getAllTestsBySubjectAndActive(Subject subject);

    void deleteById(Long id);
}
