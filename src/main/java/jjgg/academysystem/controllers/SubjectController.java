package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.Subject;
import jjgg.academysystem.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
@CrossOrigin("*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.save(subject);
        return ResponseEntity.ok(createdSubject);
    }

    @GetMapping("/{subjectId}")
    public Subject getById(@PathVariable Long subjectId) {
        try{
            return subjectService.getSubjectById(subjectId);
        } catch (Exception e){
            System.out.println("Error: no subject found with id " + subjectId);
            return null;
        }
    }

    @GetMapping("/subjectslist")
    public ResponseEntity<?> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAll());
    }

    @PutMapping("/update")
    public Subject updateSubject(@RequestBody Subject subject) {
        return subjectService.update(subject);
    }

    @DeleteMapping("/delete/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId) {
        subjectService.delete(subjectId);
    }

}
