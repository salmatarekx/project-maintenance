package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.DTO.AssignmentDetailsDTO;
import com.LMS.LMS.ServiceLayer.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<AssignmentDetailsDTO> getAssignmentDetails(@PathVariable Long id) {
        Optional<AssignmentDetailsDTO> assignmentDetails = assignmentService.getAssignmentDetails(id);

        return assignmentDetails
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping ("/GetAllAssignment")
    public ResponseEntity<List<AssignmentDetailsDTO>> getAllAssignmentDetails() {
        List<AssignmentDetailsDTO> assignmentDetails = assignmentService.getAllAssignmentDetails();

        return ResponseEntity.ok(assignmentDetails);
    }

}