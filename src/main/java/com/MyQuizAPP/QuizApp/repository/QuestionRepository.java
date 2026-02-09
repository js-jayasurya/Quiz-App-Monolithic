package com.MyQuizAPP.QuizApp.repository;

import com.MyQuizAPP.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {


    List<Question> findByCategory(String category);

    @Query(value = "SELECT TOP (:numQ) * FROM question q WHERE q.category = :category ORDER BY NEWID()", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int numQ);
}
