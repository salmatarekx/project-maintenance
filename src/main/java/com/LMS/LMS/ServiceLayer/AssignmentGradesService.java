package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentGradesService {

    private final AssignmentGradesRepo assignmentGradesRepo;
    private final AssignmentRepo       assignmentRepo;
    private final UserRepository       userRepo;

    public AssignmentGradesService(AssignmentGradesRepo assignmentGradesRepo,
                                   AssignmentRepo assignmentRepo,
                                   UserRepository userRepo) {
        this.assignmentGradesRepo = assignmentGradesRepo;
        this.assignmentRepo       = assignmentRepo;
        this.userRepo             = userRepo;
    }

    public boolean isAllowedFileType(String fileType) {
        return fileType != null && (
                fileType.equals("application/pdf") ||
                        fileType.equals("application/msword") ||
                        fileType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
        );
    }

    public AssignmentGrades submitAssignment(Long assignmentId,
                                             Long studentId,
                                             MultipartFile file) throws IOException {
        Optional<Assignment> assignment = assignmentRepo.findById(assignmentId);
        Optional<User>       student    = userRepo.findById(studentId);

        if (assignment.isPresent() && student.isPresent()) {
            // Validate file type
            String fileType = file.getContentType();
            if (!isAllowedFileType(fileType)) {
                throw new IllegalArgumentException("Invalid file type. Only PDF and DOC files are allowed.");
            }

            AssignmentGrades submission = new AssignmentGrades();
            submission.setAssignment(assignment.get());
            submission.setStudent(student.get());
            submission.setSubmissionDate(LocalDateTime.now());
            submission.setFileName(file.getOriginalFilename());
            submission.setFileType(fileType);
            submission.setFileData(file.getBytes());

            return assignmentGradesRepo.save(submission);
        }
        return null;
    }

    public byte[] getSubmissionFile(Long submissionId) {
        Optional<AssignmentGrades> submission = assignmentGradesRepo.findById(submissionId);
        return submission.map(AssignmentGrades::getFileData).orElse(null);
    }

    public AssignmentGrades gradeSubmission(Long submissionId, String grade, String feedback) {
        Optional<AssignmentGrades> submission = assignmentGradesRepo.findById(submissionId);
        if (submission.isPresent()) {
            AssignmentGrades graded = submission.get();
            graded.setGrade(grade);
            graded.setFeedback(feedback);
            return assignmentGradesRepo.save(graded);
        }
        return null;
    }

    public List<AssignmentGrades> getAssignmentsGrades(long studentId) {
        Optional<User> student = userRepo.findById(studentId);
        return student
                .map(u -> assignmentGradesRepo.findByStudent(u))
                .orElse(null);
    }

    public List<AssignmentGrades> getAssignmentGrade(long assignmentId) {
        Optional<Assignment> assignment = assignmentRepo.findById(assignmentId);
        return assignment
                .map(a -> assignmentGradesRepo.findByAssignment(a))
                .orElse(null);
    }
}
