package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ServiceLayer.AssignmentGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment-grades")
public class AssignmentGradesController {

    @Autowired
    private AssignmentGradesService gradesService;

    @PostMapping("/submit")
    public ResponseEntity<AssignmentGrades> submitAssignment(
            @RequestParam Long assignmentId,
            @RequestParam Long studentId,
            @RequestBody String submissionContent) {
        AssignmentGrades submission = gradesService.submitAssignment(assignmentId, studentId, submissionContent);
        return submission != null ?
                ResponseEntity.ok(submission) :
                ResponseEntity.badRequest().build();
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