package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.Question;
import jjgg.academysystem.entities.Test;
import jjgg.academysystem.services.QuestionService;
import jjgg.academysystem.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private TestService testService;

    @GetMapping("/{questionId}")
    public Question getById(@PathVariable Long questionId) {
        return questionService.getQuestionById(questionId);
    }

    @GetMapping("/questionslist")
        public ResponseEntity<?> getAllQuestions() {
            return ResponseEntity.ok(questionService.getAll());
        }

    @GetMapping("/test/{testId}")
        public ResponseEntity<?> getQuestionsByTest(@PathVariable Long testId) {
            Test test = testService.getById(testId);
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
            return ResponseEntity.ok(questionService.save(question));
        }

    @PostMapping("/evaluateTest")
        public ResponseEntity<?> evaluateTest(@RequestBody List<Question> questions) {
            double pointsEarned = 0;
            Integer correctAnswers = 0;
            Integer attempts = 0;
            for(Question q : questions){
                Question question = questionService.ListQuestion(q.getQuestionId());
                if(question.getCorrectOption().equals(q.getSelectedAnswer())){
                    correctAnswers++;
                    double points = Double.parseDouble(questions.get(0).getTest().getMaxPoints())/questions.size();
                    pointsEarned += points;
                }
                if(q.getSelectedAnswer() != null){
                    attempts++;
                }
            }
        Map<String,Object> answers = new HashMap<>();
        answers.put("cantCorrect",correctAnswers);
        answers.put("attempts",attempts);
        answers.put("maxPoints",pointsEarned);
        return ResponseEntity.ok(answers);
        }

    @PutMapping("/update")
        public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
            Question updatedQuestion = questionService.update(question);
            return ResponseEntity.ok(updatedQuestion);
        }

    @DeleteMapping("/delete/{questionId}")
        public void deleteQuestion(@PathVariable Long questionId) {
            questionService.delete(questionId);
        }
}
