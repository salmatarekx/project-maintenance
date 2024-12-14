package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentGradesService {

    @Autowired
    private AssignmentGradesRepo assignmentGradesRepo;

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private UserRepository userRepo;

    public AssignmentGrades submitAssignment(Long assignmentId, Long studentId, String submissionContent) {
        Optional<Assignment> assignment = assignmentRepo.findById(assignmentId);
        Optional<User> student = userRepo.findById(studentId);

        if (assignment.isPresent() && student.isPresent()) {
            AssignmentGrades submission = new AssignmentGrades();
            submission.setAssignment(assignment.get());
            submission.setStudent(student.get());
            submission.setSubmissionContent(submissionContent);
            submission.setSubmissionDate(LocalDateTime.now());
            return assignmentGradesRepo.save(submission);
        }
        return null;
    }

    public AssignmentGrades gradeSubmission(Long submissionId, String grade, String feedback) {
        Optional<AssignmentGrades> submission = assignmentGradesRepo.findById(submissionId);
        if (submission.isPresent()) {
            AssignmentGrades gradeSubmission = submission.get();
            gradeSubmission.setGrade(grade);
            gradeSubmission.setFeedback(feedback);
            return assignmentGradesRepo.save(gradeSubmission);
        }
        return null;
    }

    public List<AssignmentGrades> getStudentGrades(long studentId) {
        Optional<User> student = userRepo.findById(studentId);
        if (student.isPresent()) {
            return assignmentGradesRepo.findByStudent(student.get());
        }
        return null;
    }

    public List<AssignmentGrades> getAssignmentGrades(long assignmentId) {
        Optional<Assignment> assignment = assignmentRepo.findById(assignmentId);
        if (assignment.isPresent()) {
            return assignmentGradesRepo.findByAssignment(assignment.get());
        }
        return null;
    }
}