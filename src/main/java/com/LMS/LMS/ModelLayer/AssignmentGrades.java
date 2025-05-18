package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class AssignmentGrades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // make student required
    @NotNull(message = "Student cannot be null")
    private User student;

    @ManyToOne(optional = false) // make assignment required
    @NotNull(message = "Assignment cannot be null")
    private Assignment assignment;

    @NotBlank(message = "Grade cannot be blank")
    private String grade;

    private String feedback;

    @PastOrPresent(message = "Submission date cannot be in the future")
    private LocalDateTime submissionDate;

    @Size(max = 10000, message = "Submission content too long")
    private String submissionContent;

    private Boolean isLate;

    private String fileName;

    private String fileType;

    @Lob
    @Column(name = "file_data", length = 10000000)
    private byte[] fileData;

    // Constructors
    public AssignmentGrades() {}

    public AssignmentGrades(Long id, User student, Assignment assignment,
                            String grade, String feedback, LocalDateTime submissionDate,
                            String submissionContent) {
        this.id = id;
        this.student = student;
        this.assignment = assignment;
        this.grade = grade;
        this.feedback = feedback;
        this.submissionDate = submissionDate;
        this.submissionContent = submissionContent;
        updateIsLate();
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getStudent() { return student; }
    public void setStudent(User student) {
        this.student = student;
    }

    public Assignment getAssignment() { return assignment; }
    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
        updateIsLate();
    }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public LocalDateTime getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
        updateIsLate();
    }

    public String getSubmissionContent() { return submissionContent; }
    public void setSubmissionContent(String submissionContent) { this.submissionContent = submissionContent; }

    public Boolean getIsLate() { return isLate; }

    private void updateIsLate() {
        this.isLate = submissionDate != null && assignment != null &&
                submissionDate.isAfter(assignment.getDueDate());
    }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public byte[] getFileData() { return fileData; }
    public void setFileData(byte[] fileData) { this.fileData = fileData; }
}
