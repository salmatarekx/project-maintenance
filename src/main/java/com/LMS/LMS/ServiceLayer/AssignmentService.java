package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.DTO.AssignmentDetailsDTO;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;



    @Autowired
    private AssignmentGradesRepo gradesRepo;

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }

    public Optional<Assignment> getAssignment(Long id) {
        return assignmentRepo.findById(id);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    public AssignmentGrades submitAssignment(AssignmentGrades submission) {
        return gradesRepo.save(submission);
    }

    public Optional<AssignmentDetailsDTO> getAssignmentDetails(Long id) {
        Optional<Assignment> assignment = assignmentRepo.findById(id);

        return assignment.map(a -> new AssignmentDetailsDTO(
                a.getId(),
                a.getTitle(),
                a.getDueDate(),
                a.getMaxScore(),
                a.getCourse().getTitle(),
                a.getCourse().getInstructor().getUserName()
        ));
    }
    public List<AssignmentDetailsDTO> getAllAssignmentDetails() {
        List<Assignment> assignments = assignmentRepo.findAll();

        return assignments.stream()
                .map(a -> new AssignmentDetailsDTO(
                        a.getId(),
                        a.getTitle(),

                        a.getDueDate(),
                        a.getMaxScore(),
                        a.getCourse().getTitle(),
                        a.getCourse().getInstructor().getUserName()
                ))
                .collect(Collectors.toList());
    }
}