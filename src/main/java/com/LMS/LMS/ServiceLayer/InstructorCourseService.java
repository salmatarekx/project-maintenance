package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.Course;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.CourseRepository;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorCourseService {
    private final CourseService courseService ;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository ;

    public InstructorCourseService(CourseService courseService, UserRepository userRepository, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }
    public Course CreateCourse(CourseDTO courseDTO , User user){
       return courseService.createCourse(courseDTO, user) ;
    }
    public void removeStudentfromCourse(int Courseid , Long StudenId){
        Course course = courseRepository.findById(Courseid).orElse(null);
        User student = userRepository.findById(StudenId).orElse(null);
        if (course != null && student != null){
            List<User>Students = course.getStudents() ;

        }
    }
}
