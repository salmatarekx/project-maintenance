package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private AssignmentGradesRepo assignmentGradesRepo ;
    @Autowired
    private AssignmentRepo assignmentRepo ;
    @Autowired
    private UserRepository userRepository ;

    public QuizGrades takeQuiz(Long quizId, Long studentId) {
        // Start a new quiz attempt
        return quizService.startQuiz(quizId, studentId);
    }


    public AssignmentGrades handInAssignment(Long assignmentId, Long studentId, MultipartFile file) {
        try {
            // Submit an assignment with file handling
            return assignmentGradesService.submitAssignment(assignmentId, studentId, file);
        } catch (IOException e) {
            // Handle the IOException from file processing
            throw new RuntimeException("Error processing assignment submission file: " + e.getMessage(), e);
        }
    }
    public List<AssignmentGrades> viewAssignmentsGrades(Long studentId) {
        // View student's assignment grades
        return assignmentGradesService.getAssignmentsGrades(studentId);
    }

    public List<AssignmentGrades> viewAssignmentGrade(Long assignmentId) {
        // View student's assignment grades
        return assignmentGradesService.getAssignmentGrade(assignmentId);
    }
    public AssignmentGrades ViewStudentAssignmentGrade(Long assignmentId , Long studentId){
        Assignment assignment = assignmentRepo.findById(assignmentId).orElse(null);
        User Student = userRepository.findById(studentId).orElse(null);
        AssignmentGrades assignmentGrades = assignmentGradesRepo.findByStudentAndAssignment(Student,assignment);

        return assignmentGrades ;
    }


    public List<QuizGrades> viewQuizzesGrades(Long studentId) {
        // View student's quiz grades
        return quizGradesService.getStudentQuizzesGrades(studentId);
    }

    public List<QuizGrades> viewQuizGrade(Long quizId) {
        // View student's quiz grades
        return quizGradesService.getQuizGrades(quizId);
    }
}