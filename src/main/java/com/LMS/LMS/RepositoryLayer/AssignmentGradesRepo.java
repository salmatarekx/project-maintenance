package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentGradesRepo extends JpaRepository<AssignmentGrades, Long> {
    List<AssignmentGrades> findByStudent(User student);
    List<AssignmentGrades> findByAssignment(Assignment assignment);
}