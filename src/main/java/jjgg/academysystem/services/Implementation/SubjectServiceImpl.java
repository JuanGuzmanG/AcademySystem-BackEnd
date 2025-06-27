package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Subject;
import jjgg.academysystem.repositories.SubjectReposiroty;
import jjgg.academysystem.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectReposiroty subjectRepository;

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(Subject subject) {
        Subject subjectUpdate = subjectRepository.findById(subject.getSubjectId()).get();
        subjectUpdate.setSubjectName(subject.getSubjectName());
        subjectUpdate.setSubjectDescription(subject.getSubjectDescription());
        return subjectRepository.save(subjectUpdate);
    }

    @Override
    public Set<Subject> getAll() {
        return new HashSet<>(subjectRepository.findAll());
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }
}
