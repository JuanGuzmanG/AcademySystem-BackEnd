package jjgg.academysystem.services;

import jjgg.academysystem.entities.Question;
import jjgg.academysystem.entities.Test;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface QuestionService {

    Question save(Question question);

    Question update(Question question);

    Set<Question> getAll();

    Set<Question> getByTest(Test test);

    Question getQuestionById(Long id);

    void delete(Long id);
}
