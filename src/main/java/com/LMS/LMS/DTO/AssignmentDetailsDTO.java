package com.LMS.LMS.DTO;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class AssignmentDetailsDTO {

    @NotNull(message = "Assignment ID cannot be null")
    private Long assignmentId;

    @NotBlank(message = "Assignment title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String assignmentTitle;

    @NotNull(message = "Due date is required")
    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    @NotNull(message = "Max score is required")
    private Double maxScore;

    @NotBlank(message = "Course title is required")
    private String courseTitle;

    @NotBlank(message = "Course instructor is required")
    private String courseInstructor;

    public AssignmentDetailsDTO() {}

    public AssignmentDetailsDTO(Long assignmentId, String assignmentTitle,
                                LocalDateTime dueDate, Double maxScore,
                                String courseTitle, String courseInstructor) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
        this.courseTitle = courseTitle;
        this.courseInstructor = courseInstructor;
    }

    // Getters
    public Long getAssignmentId() { return assignmentId; }
    public String getAssignmentTitle() { return assignmentTitle; }
    public LocalDateTime getDueDate() { return dueDate; }
    public Double getMaxScore() { return maxScore; }
    public String getCourseTitle() { return courseTitle; }
    public String getCourseInstructor() { return courseInstructor; }

}
