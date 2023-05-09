package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

//    get questions of quiz
    @GetMapping("/quiz/{id}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable Long id){
//        Quiz quiz = new Quiz();
//        quiz.setQId(id);
//        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Quiz quiz = this.quizService.getQuiz(id);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())){
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

//    get single question
    @GetMapping("/{id}")
    public Question getQuestion(@PathVariable Long id){
        return this.questionService.getQuestion(id);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id){
        this.questionService.deleteQuestion(id);
        return "Deleted Successfully";
    }
}
