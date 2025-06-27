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

    @GetMapping("/{testId}")
    public ResponseEntity<Test> getTest(@PathVariable Long testId) {
        return ResponseEntity.ok(testService.getById(testId));
    }

    @GetMapping("/testlist")
    public ResponseEntity<?> getAllTests() {
        return ResponseEntity.ok(testService.findAll());
    }

    @GetMapping("/subject/{subjectId}")
        public List<Test> getAllTestsBySubject(@PathVariable Long subjectId) {
            Subject subject = new Subject();
            subject.setSubjectId(subjectId);
            return testService.getAllTestsBySubject(subject);
        }

    @GetMapping("/active")
        public List<Test> getAllActiveTests() {
            return testService.getAllTestsByActive();
        }

    @GetMapping("/active/{subjectId}")
        public List<Test> getAllActiveTestsBySubject(@PathVariable Long subjectId) {
            Subject subject = new Subject();
            subject.setSubjectId(subjectId);
            return testService.getAllTestsBySubjectAndActive(subject);
        }

    @PostMapping("/add")
        public ResponseEntity<Test> addQuestion(@RequestBody Test test) {
            return ResponseEntity.ok(testService.save(test));
        }

    @PutMapping("/update")
        public ResponseEntity<Test> updateTest(@RequestBody Test test) {
            return ResponseEntity.ok(testService.update(test));
        }

    @DeleteMapping("/delete/{testId}")
        public void deleteTest(@PathVariable Long testId) {
            testService.delete(testId);
        }
}
