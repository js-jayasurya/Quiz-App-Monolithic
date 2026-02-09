package com.MyQuizAPP.QuizApp.service;

import com.MyQuizAPP.QuizApp.model.Question;
import com.MyQuizAPP.QuizApp.model.QuestionWrapper;
import com.MyQuizAPP.QuizApp.model.Quiz;
import com.MyQuizAPP.QuizApp.model.Response;
import com.MyQuizAPP.QuizApp.repository.QuestionRepository;
import com.MyQuizAPP.QuizApp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List< Question> questions = questionRepository.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("sucess", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for(Question q : questionFromDb) {
            QuestionWrapper  qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }


        return new ResponseEntity<>(questionForUser,HttpStatus.OK);

    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> question = quiz.get().getQuestions();
        int right =0;
        int i =0 ;
        for(Response response : responses) {
            if(response.getResponse().equals(question.get(i).getRightAnswer())) {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
