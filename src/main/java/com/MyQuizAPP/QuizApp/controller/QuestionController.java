package com.MyQuizAPP.QuizApp.controller;

import com.MyQuizAPP.QuizApp.model.Question;
import com.MyQuizAPP.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "allQuestions")
    public ResponseEntity<List<Question>> allQuestions() {

        return questionService.getAllQuestions();
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category ) {

        return questionService.getQuestionByCategory(category);
    }

    @PostMapping(value ="add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
