package com.LMS.LMS.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

    private final CourseService courseService;
    private final TrackingPerformanceService trackingPerformanceService ; ;
    @Autowired
    public StudentCourseService(@Lazy CourseService courseService,@Lazy TrackingPerformanceService trackingPerformanceService) {
        this.courseService = courseService;
        this.trackingPerformanceService = trackingPerformanceService;
    }
    public void Enroll(int courseId , Long StudentId){
        courseService.enrollStudent(courseId , StudentId);
    }
    public void AccessCourseMaterials(){
        // menna
    }
    public void TakeQuiz(){
        // Taha
    }
    public void HandInAssignment(){
        //Taha
    }
    public void ViewAssignmentGrades(int courseId , Long StudentId){

    }
    public void ViewQuizGrades(int courseId , Long studentId){

    }



}
