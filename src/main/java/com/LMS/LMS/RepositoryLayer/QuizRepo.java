package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
