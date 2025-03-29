package jjgg.academysystem.services;

import jjgg.academysystem.entities.Test;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface TestService {

    Test save(Test test);

    Test update(Test test);

    Set<Test> findAll();

    Test getById(Long id);

    void deleteById(Long id);
}
