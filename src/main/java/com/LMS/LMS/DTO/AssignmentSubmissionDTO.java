package com.LMS.LMS.DTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class AssignmentSubmissionDTO {

    @NotNull(message = "Assignment ID is required")
    private Long assignmentId;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "File must be provided")
    private MultipartFile file;

    // Getters and setters
    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}
