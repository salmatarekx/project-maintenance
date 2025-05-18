package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ServiceLayer.AssignmentGradesService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/assignment-grades")
@Validated // enables validation on method parameters
public class AssignmentGradesController {

    private final AssignmentGradesService gradesService;

    public AssignmentGradesController(AssignmentGradesService gradesService) {
        this.gradesService = gradesService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAssignment(
            @RequestParam @NotNull(message = "Assignment ID is required") Long assignmentId,
            @RequestParam @NotNull(message = "Student ID is required") Long studentId,
            @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Uploaded file is empty");
            }

            AssignmentGrades submission = gradesService.submitAssignment(assignmentId, studentId, file);
            return submission != null ?
                    ResponseEntity.ok(submission) :
                    ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file upload: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
    public ResponseEntity<Object> submitAssignment(
            @RequestParam("assignmentId") Long assignmentId,
            @RequestParam("studentId")    Long studentId,
            @RequestParam("file")         MultipartFile file
    ) throws IOException {

        // empty‐file check
        if (file.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("No file uploaded. Please attach a PDF or DOC file.");
        }

        // type‐check
        String ct = file.getContentType();
        if (!gradesService.isAllowedFileType(ct)) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid file type. Only PDF and DOC files are allowed.");
        }

        // delegate
        AssignmentGrades submission = gradesService
                .submitAssignment(assignmentId, studentId, file);

        return ResponseEntity.ok(submission);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParam(MissingServletRequestParameterException ex) {
        if ("file".equals(ex.getParameterName())) {
            return ResponseEntity
                    .badRequest()
                    .body("No file uploaded. Please attach a PDF or DOC file.");
        }
        return ResponseEntity
                .badRequest()
                .body("Required parameter '" + ex.getParameterName() + "' is missing");
    }

    @GetMapping("/download/{submissionId}")
    public ResponseEntity<byte[]> downloadSubmission(
            @PathVariable @NotNull(message = "Submission ID is required") Long submissionId) {
        AssignmentGrades submission = gradesService.getAssignmentsGrades(submissionId)
                .stream()
                .findFirst()
                .orElse(null);

        if (submission != null && submission.getFileData() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + submission.getFileName() + "\"")
                    .contentType(MediaType.parseMediaType(submission.getFileType()))
                    .body(submission.getFileData());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{submissionId}/grade")
    public ResponseEntity<?> gradeSubmission(
            @PathVariable @NotNull(message = "Submission ID is required") Long submissionId,
            @RequestParam @NotBlank(message = "Grade must not be blank") String grade,
            @RequestParam @NotBlank(message = "Feedback must not be blank") String feedback) {

        AssignmentGrades graded = gradesService.gradeSubmission(submissionId, grade, feedback);
        return graded != null ?
                ResponseEntity.ok(graded) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Submission not found");
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssignmentGrades>> getStudentGrades(
            @PathVariable @NotNull(message = "Student ID is required") Long studentId) {

        List<AssignmentGrades> grades = gradesService.getAssignmentsGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }
}
