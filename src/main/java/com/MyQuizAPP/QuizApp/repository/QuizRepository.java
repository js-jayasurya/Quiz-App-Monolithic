package com.MyQuizAPP.QuizApp.repository;

import com.MyQuizAPP.QuizApp.model.QuestionWrapper;
import com.MyQuizAPP.QuizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {


}
