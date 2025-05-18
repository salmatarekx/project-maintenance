package com.LMS.LMS.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

    private final CourseService courseService;

    @Autowired
    public StudentCourseService(@Lazy CourseService courseService) {
        this.courseService = courseService;
    }
    public void Enroll(Long courseId , Long StudentId){
        courseService.enrollStudent(courseId , StudentId);
    }
    public void AccessCourseMaterials(){
    }




}
