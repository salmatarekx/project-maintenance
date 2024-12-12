package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.Course;
import org.springframework.stereotype.Service;

@Service
public class AdminCourseService {
    private final CourseService courseService;


    public AdminCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
    public Course CreateCourse(CourseDTO courseDTO){
        return courseService.createCourse(courseDTO);
    }
    public void DeleteCourse(int ID){
        courseService.deleteCourse(ID);
    }
    pub
}
