package com.MyQuizAPP.QuizApp.controller;

import com.MyQuizAPP.QuizApp.model.QuestionWrapper;
import com.MyQuizAPP.QuizApp.model.Response;
import com.MyQuizAPP.QuizApp.service.QuestionService;
import com.MyQuizAPP.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping(value = "create")
    public ResponseEntity<String> create(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {

        return quizService.createQuiz(category, numQ, title);

    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping(value ="submit/{id}")
    public ResponseEntity<Integer> quizSubmit(@PathVariable Integer id, @RequestBody List<Response> responses) {

        return quizService.calculateResult(id, responses);
    }
}
