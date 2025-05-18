package com.LMS.LMS.DTO;

import com.LMS.LMS.ModelLayer.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class DeleteCourseRequest {

    @Valid
    @NotNull(message = "Course details are required")
    private CourseDTO courseDTO;

    @NotNull(message = "Current user is required")
    private User currentUser;

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}


