package com.LMS.LMS.ModelLayer;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Integer maxScore;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private List<AssignmentGrades> grades;

    // Constructors
    public Assignment() {}

    public Assignment(Integer id, String title, String description, LocalDateTime dueDate,
                      Integer maxScore, Course course) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
        this.course = course;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public List<AssignmentGrades> getGrades() { return grades; }
    public void setGrades(List<AssignmentGrades> grades) { this.grades = grades; }
}