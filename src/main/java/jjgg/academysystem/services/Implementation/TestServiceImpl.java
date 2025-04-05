package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Subject;
import jjgg.academysystem.entities.Test;
import jjgg.academysystem.repositories.TestRepository;
import jjgg.academysystem.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test update(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Set<Test> findAll() {
        return new LinkedHashSet<>(testRepository.findAll());
    }

    @Override
    public Test getById(Long id) {
        return testRepository.findById(id).get();
    }

    @Override
    public List<Test> getAllTestsBySubject(Subject subject) {
        return this.testRepository.findBySubject(subject);
    }

    @Override
    public List<Test> getAllTestsByActive() {
        return testRepository.findByActive(true);
    }

    @Override
    public List<Test> getAllTestsBySubjectAndActive(Subject subject) {
        return testRepository.findBySubjectAndActive(subject,true);
    }

    @Override
    public void deleteById(Long id) {
        Test test = new Test();
        test.setIdTest(id);
        testRepository.delete(test);
    }
}
