package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Question;
import jjgg.academysystem.entities.Test;
import jjgg.academysystem.repositories.QuestionRepository;
import jjgg.academysystem.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question update(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> getAll() {
        return (Set<Question>) questionRepository.findAll();
    }

    @Override
    public Set<Question> getByTest(Test test) {
        return questionRepository.findByTest(test);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        Test test = new Test();
        test.setIdTest(id);
        questionRepository.deleteById(id);
    }

}
