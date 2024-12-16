package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ServiceLayer.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        try {
            return ResponseEntity.ok(assignmentService.createAssignment(assignment));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/GetAssignment/{id}")
    public ResponseEntity<Assignment> getAssignment(@PathVariable Long id) {
        return assignmentService.getAssignment(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
}