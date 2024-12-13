package com.LMS.LMS.ServiceLayer;
import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.Course;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.CourseRepository;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseService(@Lazy CourseRepository courseRepository,@Lazy UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(CourseDTO courseDTO, User currentUser) {
        if (currentUser.getRole().equals("Student")) {
            throw new RuntimeException("You do not have permission to create a course");
        }
        User instructor;
            instructor = userRepository.findById((long) courseDTO.getInstructor().getID())
                    .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());
//        course.setMediaFiles(courseDTO.getMediaFiles());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course updateCourse(int id, CourseDTO courseDTO, User currentUser) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (currentUser.getRole().equals("Student")) {
            throw new RuntimeException("You do not have permission to update this course");
        }

        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());
       // course.setMediaFiles(courseDTO.getMediaFiles());

        return courseRepository.save(course);
    }

    public void deleteCourse(int id, User currentUser) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (currentUser.getRole().equals("Student")) {
            throw new RuntimeException("You do not have permission to delete this course");
        }

        courseRepository.deleteById(id);
    }

    public void enrollStudent(int courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        course.getStudents().add(student);
        courseRepository.save(course);
    }

    public List<User> getEnrolledStudents(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return course.getStudents();
    }
}


