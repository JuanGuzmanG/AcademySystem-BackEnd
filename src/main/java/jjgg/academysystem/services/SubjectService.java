package jjgg.academysystem.services;

import jjgg.academysystem.entities.Subject;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface SubjectService {

    Subject save(Subject subject);

    Subject update(Subject subject);

    Set<Subject> getAll();

    Subject getSubjectById(Long id);

    void delete(Long id);
}
