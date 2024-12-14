package com.LMS.LMS.ModelLayer;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AssignmentGrades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Assignment assignment;

    private String grade;
    private String feedback;
    private LocalDateTime submissionDate;
    private String submissionContent;
    private Boolean isLate;

    // Constructors
    public AssignmentGrades() {}

    public AssignmentGrades(Integer id, User student, Assignment assignment,
                            String grade, String feedback, LocalDateTime submissionDate,
                            String submissionContent) {
        this.id = id;
        this.student = student;
        this.assignment = assignment;
        this.grade = grade;
        this.feedback = feedback;
        this.submissionDate = submissionDate;
        this.submissionContent = submissionContent;
        this.isLate = submissionDate != null && assignment != null &&
                submissionDate.isAfter(assignment.getDueDate());
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public Assignment getAssignment() { return assignment; }
    public void setAssignment(Assignment assignment) { this.assignment = assignment; }

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
    public void setSubmissionContent(String submissionContent) {
        this.submissionContent = submissionContent;
    }

    public Boolean getIsLate() { return isLate; }

    private void updateIsLate() {
        this.isLate = submissionDate != null && assignment != null &&
                submissionDate.isAfter(assignment.getDueDate());
    }
}