package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Subject;
import jjgg.academysystem.repositories.SubjectReposiroty;
import jjgg.academysystem.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectReposiroty subjectReposiroty;

    @Override
    public Subject save(Subject subject) {
        return subjectService.save(subject);
    }

    @Override
    public Subject update(Subject subject) {
        return subjectService.update(subject);
    }

    @Override
    public Set<Subject> getAll() {
        return new LinkedHashSet<>(subjectService.getAll());
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectService.getSubjectById(id);
    }

    @Override
    public void delete(Long id) {
        Subject subject = new Subject();
        subject.setIdSubject(id);
        subjectService.delete(id);
    }
}
