package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;

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

    public AssignmentGrades(Integer id, User student,Assignment assignment, String grade, String feedback) {
        this.id = id;
        this.student = student;
        this.grade = grade;
        this.feedback = feedback;
        this.assignment = assignment;
    }

    public AssignmentGrades() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
