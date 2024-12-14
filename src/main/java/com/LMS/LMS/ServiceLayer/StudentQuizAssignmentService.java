package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.QuizGrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentQuizAssignmentService {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizGradesService quizGradesService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentGradesService assignmentGradesService;

    public QuizGrades takeQuiz(Long quizId, Long studentId) {
        // Start a new quiz attempt
        return quizService.startQuiz(quizId, studentId);
    }

    public AssignmentGrades handInAssignment(Long assignmentId, Long studentId, String submissionContent) {
        // Submit an assignment
        return assignmentGradesService.submitAssignment(assignmentId, studentId, submissionContent);
    }

    public List<AssignmentGrades> viewAssignmentGrades(Long studentId) {
        // View student's assignment grades
        return assignmentGradesService.getStudentGrades(studentId);
    }

    public List<QuizGrades> viewQuizGrades(Long studentId) {
        // View student's quiz grades
        return quizGradesService.getStudentQuizGrades(studentId);
    }
}