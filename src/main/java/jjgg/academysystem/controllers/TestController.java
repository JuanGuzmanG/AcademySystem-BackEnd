package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.Subject;
import jjgg.academysystem.entities.Test;
import jjgg.academysystem.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/{idTest}")
    public ResponseEntity<Test> getTest(@PathVariable Long idTest) {
        return ResponseEntity.ok(testService.getById(idTest));
    }

    @GetMapping("/testlist")
    public ResponseEntity<?> getAllTests() {
        return ResponseEntity.ok(testService.findAll());
    }

    @GetMapping("/subject/{idSubject}")
        public List<Test> getAllTestsBySubject(@PathVariable Long idSubject) {
            Subject subject = new Subject();
            subject.setIdSubject(idSubject);
            return testService.getAllTestsBySubject(subject);
        }

    @GetMapping("/active")
        public List<Test> getAllActiveTests() {
            return testService.getAllTestsByActive();
        }

    @GetMapping("/active/{subjectId}")
        public List<Test> getAllActiveTestsBySubject(@PathVariable Long subjectId) {
            Subject subject = new Subject();
            subject.setIdSubject(subjectId);
            return testService.getAllTestsBySubjectAndActive(subject);
        }

    @PostMapping("/add")
        public ResponseEntity<Test> addQuestion(@RequestBody Test test) {
            return ResponseEntity.ok(testService.save(test));
        }

    @PutMapping("/update")
        public ResponseEntity<Test> updateQuestion(@RequestBody Test test) {
            return ResponseEntity.ok(testService.update(test));
        }

    @DeleteMapping("/delete/{idTest}")
        public void deleteTest(@PathVariable Long idTest) {
            testService.deleteById(idTest);
        }
}
