package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ModelLayer.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizGradesRepo extends JpaRepository<QuizGrades, Long> {

    @Query("SELECT qg FROM QuizGrades qg WHERE qg.student = :student")
    List<QuizGrades> findByStudent(@Param("student") User student);

    @Query("SELECT qg FROM QuizGrades qg WHERE qg.quiz = :quiz")
    List<QuizGrades> findByQuiz(@Param("quiz") Quiz quiz);

    @Query("SELECT COUNT(qg) FROM QuizGrades qg WHERE qg.quiz = :quiz AND qg.student = :student")
    Long countByQuizAndStudent(@Param("quiz") Quiz quiz, @Param("student") User student);

    @Query("SELECT qg FROM QuizGrades qg WHERE LOWER(qg.quiz.courseName) = LOWER(:courseName)")
    List<QuizGrades> findByCourseName(@Param("courseName") String courseName);
}
