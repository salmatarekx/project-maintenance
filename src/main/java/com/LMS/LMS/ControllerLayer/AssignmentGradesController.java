package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ServiceLayer.AssignmentGradesService;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/assignment-grades")
public class AssignmentGradesController {

    private final AssignmentGradesService gradesService;

    public AssignmentGradesController(AssignmentGradesService gradesService) {
        this.gradesService = gradesService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Object> submitAssignment(
            @RequestParam("assignmentId") Long assignmentId,
            @RequestParam("studentId")    Long studentId,
            @RequestParam("file")         MultipartFile file
    ) throws IOException {

        // empty‐file check
        if (file.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("No file uploaded. ");
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
                    .body("No file uploaded.");
        }
        return ResponseEntity
                .badRequest()
                .body("Required parameter '" + ex.getParameterName() + "' is missing");
    }

    @GetMapping("/download/{submissionId}")
    public ResponseEntity<byte[]> downloadSubmission(@PathVariable Long submissionId) {
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
    public ResponseEntity<AssignmentGrades> gradeSubmission(
            @PathVariable Long submissionId,
            @RequestParam String grade,
            @RequestParam String feedback) {
        AssignmentGrades graded = gradesService.gradeSubmission(submissionId, grade, feedback);
        return graded != null ?
                ResponseEntity.ok(graded) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssignmentGrades>> getStudentGrades(@PathVariable Long studentId) {
        List<AssignmentGrades> grades = gradesService.getAssignmentsGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }
}
