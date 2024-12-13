package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.Course;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminCourseService {
    private final CourseService courseService;


    public AdminCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public Course createCourse(CourseDTO courseDTO, User currentUser) {
        return courseService.createCourse(courseDTO, currentUser);
    }

    public void deleteCourse(int id, User currentUser) {
        courseService.deleteCourse(id, currentUser);
    }

}
