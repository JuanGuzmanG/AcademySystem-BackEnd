package jjgg.academysystem.services;

import jjgg.academysystem.entities.Question;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface QuestionService {

    Question save(Question question);

    Question update(Question question);

    Set<Question> getAll();

    Question getQuestionById(Long id);

    void delete(Long id);

    Question ListQuestion(Long id);
}
