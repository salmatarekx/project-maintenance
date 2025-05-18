package com.LMS.LMS.DTO;

import com.LMS.LMS.ModelLayer.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CourseDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Duration is required")
    private String duration;

    @Size(min = 1, message = "At least one media file is required")
    private List<@NotBlank(message = "Media file name cannot be blank") String> mediaFiles;

    @NotNull(message = "Instructor must be provided")
    private User instructor;

// Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getMediaFiles() {
        return mediaFiles;
    }

    public void setMediaFiles(List<String> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }
}
