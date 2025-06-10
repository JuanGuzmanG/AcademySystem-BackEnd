package jjgg.academysystem.controllers;

import jjgg.academysystem.entities.Result;
import jjgg.academysystem.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
@CrossOrigin("*")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/")
    public ResponseEntity<Result> saveResult(@RequestBody Result result) {
        Result savedResult = resultService.saveResult(result);
        return ResponseEntity.ok(savedResult);
    }

    @GetMapping("/count/user/{userDocument}/test/{testId}")
    public ResponseEntity<Long> getAttemptCount(
            @PathVariable("userDocument") Long userDocument,
            @PathVariable("testId") Long testId) {
        Long count = resultService.getAttemptCountForUser(userDocument, testId);
        return ResponseEntity.ok(count);
    }
}