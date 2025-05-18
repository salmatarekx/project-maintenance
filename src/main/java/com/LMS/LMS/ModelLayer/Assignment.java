package com.LMS.LMS.ModelLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    @Size(max = 2000, message = "Description must be at most 2000 characters")
    private String description;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDateTime dueDate;

    @NotNull(message = "Max score is required")
    @Positive(message = "Max score must be positive")
    private Long maxScore;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore // Prevent serialization to avoid infinite loop
    private Course course;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instructor_id", nullable = false)
    @NotNull(message = "Instructor is required")
    private User instructor;

    // Constructors
    public Assignment() {}

    public Assignment(Long id, String title, String description,
                      LocalDateTime dueDate, Long maxScore, Course course) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
        this.course = course;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public Long getMaxScore() { return maxScore; }
    public void setMaxScore(Long maxScore) { this.maxScore = maxScore; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public User getInstructor() {
        return instructor;
    }
    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }
}
