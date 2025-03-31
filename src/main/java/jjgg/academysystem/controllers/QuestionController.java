package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.Question;
import jjgg.academysystem.entities.Test;
import jjgg.academysystem.services.QuestionService;
import jjgg.academysystem.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private TestService testService;

    @GetMapping("/{idQuestion}")
    public Question getById(@PathVariable Long idQuestion) {
        return questionService.getQuestionById(idQuestion);
    }

    @GetMapping("/questionslist")
        public ResponseEntity<?> getAllQuestions() {
            return ResponseEntity.ok(questionService.getAll());
        }

    @GetMapping("/question/{idTest}")
        public ResponseEntity<?> getQuestionsByTest(@PathVariable Long idTest) {
            Test test = testService.getById(idTest);
            Set<Question> questions = test.getQuestions();

            List tests = new ArrayList<>(questions);
            if(tests.size() > test.getCantQuestions()){
                tests = tests.subList(0, test.getCantQuestions()+1);
            }

            Collections.shuffle(tests);
            return ResponseEntity.ok(tests);
        }

    @PostMapping("/add")
        public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
            Question createdQuestion = questionService.save(question);
            return ResponseEntity.ok(createdQuestion);
        }

    @PutMapping("update")
        public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
            Question updatedQuestion = questionService.update(question);
            return ResponseEntity.ok(updatedQuestion);
        }

    @DeleteMapping("/delete/{idQuestion}")
        public ResponseEntity<?> deleteQuestion(@PathVariable Long idQuestion) {
            questionService.delete(idQuestion);
            return ResponseEntity.ok("Question deleted successfully");
        }
}
