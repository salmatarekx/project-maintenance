package com.LMS.LMS.DTO;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class QuizDetailsDTO {

    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Start time cannot be null")
    @FutureOrPresent(message = "Start time must be present or future")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    @FutureOrPresent(message = "End time must be present or future")
    private LocalDateTime endTime;

    @NotNull(message = "Max score cannot be null")
    @Positive(message = "Max score must be positive")
    private Long maxScore;

    @NotBlank(message = "Course title cannot be blank")
    private String courseTitle;

    @NotBlank(message = "Course instructor cannot be blank")
    private String courseInstructor;

    public QuizDetailsDTO(Long id, String title, LocalDateTime startTime,
                          LocalDateTime endTime, Long maxScore, String courseTitle,
                          String courseInstructor) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxScore = maxScore;
        this.courseTitle = courseTitle;
        this.courseInstructor = courseInstructor;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCourseTitle() { return courseTitle; }
    public String getCourseInstructor() { return courseInstructor; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Long getMaxScore() { return maxScore; }
}
