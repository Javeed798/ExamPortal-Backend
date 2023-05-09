package com.exam.controller;

import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        Quiz quiz1 = this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }

    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        Quiz quiz1 = this.quizService.updateQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }

    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id){
        return ResponseEntity.ok(this.quizService.getQuiz(id));
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id){
        this.quizService.delete(id);
    }


}
