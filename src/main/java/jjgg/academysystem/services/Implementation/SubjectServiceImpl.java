package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Subject;
import jjgg.academysystem.repositories.SubjectReposiroty;
import jjgg.academysystem.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectReposiroty subjectReposiroty;

    @Override
    public Subject save(Subject subject) {
        return subjectReposiroty.save(subject);
    }

    @Override
    public Subject update(Subject subject) {
        return subjectReposiroty.save(subject);
    }

    @Override
    public Set<Subject> getAll() {
        return new HashSet<>(subjectReposiroty.findAll());
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectReposiroty.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        Subject subject = new Subject();
        subject.setIdSubject(id);
        subjectReposiroty.delete(subject);
    }
}
