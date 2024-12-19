//package com.LMS.LMS.ServiceLayerTest;
//
//import com.LMS.LMS.DTO.CourseDTO;
//import com.LMS.LMS.ModelLayer.Course;
//import com.LMS.LMS.ModelLayer.Role;
//import com.LMS.LMS.ModelLayer.User;
//import com.LMS.LMS.RepositoryLayer.CourseRepository;
//import com.LMS.LMS.RepositoryLayer.UserRepository;
//import com.LMS.LMS.ServiceLayer.CourseService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class CourseServiceTest {
//
//    private CourseRepository courseRepository;
//    private UserRepository userRepository;
//    private CourseService courseService;
//
//    @BeforeEach
//    public void setup() {
//        courseRepository = mock(CourseRepository.class);
//        userRepository = mock(UserRepository.class);
//        courseService = new CourseService(courseRepository, userRepository);
//    }
//
//    @Test
//    public void testCreateCourse_Success() {
//        User admin = new User(1L, "Admin", "admin@example.com", "admin123", Role.Admin);
//        User instructor = new User(2L, "Instructor", "instructor@example.com", "instr123", Role.Instructor);
//
//        CourseDTO courseDTO = new CourseDTO();
//        courseDTO.setTitle("Test Course");
//        courseDTO.setDescription("Test Description");
//        courseDTO.setDuration("30");
//        courseDTO.setInstructor(instructor);
//
//        when(userRepository.findById(2L)).thenReturn(Optional.of(instructor));
//        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Course createdCourse = courseService.createCourse(courseDTO, admin);
//
//        assertNotNull(createdCourse);
//        assertEquals("Test Course", createdCourse.getTitle());
//        assertEquals("Test Description", createdCourse.getDescription());
//        assertEquals("30", createdCourse.getDuration());
//        assertEquals(instructor, createdCourse.getInstructor());
//        verify(courseRepository, times(1)).save(any(Course.class));
//    }
//
//    @Test
//    public void testGetAllCourses_Success() {
//        List<Course> courses = new ArrayList<>();
//        courses.add(new Course());
//        when(courseRepository.findAll()).thenReturn(courses);
//
//        List<Course> retrievedCourses = courseService.getAllCourses();
//
//        assertNotNull(retrievedCourses);
//        assertEquals(1, retrievedCourses.size());
//        verify(courseRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetCourseById_Success() {
//        Course course = new Course();
//        course.setId(1L);
//        course.setTitle("Test Course");
//
//        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
//
//        Course retrievedCourse = courseService.getCourseById(1L);
//
//        assertNotNull(retrievedCourse);
//        assertEquals(1L, retrievedCourse.getId());
//        assertEquals("Test Course", retrievedCourse.getTitle());
//        verify(courseRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testUpdateCourse_Success() {
//        User admin = new User(1L, "Admin", "admin@example.com", "admin123", Role.Admin);
//        User instructor = new User(2L, "Instructor", "instructor@example.com", "instr123", Role.Instructor);
//        Course course = new Course();
//        course.setId(1L);
//        course.setTitle("Old Title");
//        course.setInstructor(instructor);
//
//        CourseDTO updatedCourseDTO = new CourseDTO();
//        updatedCourseDTO.setTitle("Updated Title");
//        updatedCourseDTO.setDescription("Updated Description");
//        updatedCourseDTO.setDuration("40");
//
//        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
//        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Course updatedCourse = courseService.updateCourse(1L, updatedCourseDTO, admin);
//
//        assertNotNull(updatedCourse);
//        assertEquals("Updated Title", updatedCourse.getTitle());
//        assertEquals("Updated Description", updatedCourse.getDescription());
//        assertEquals("40", updatedCourse.getDuration());
//        verify(courseRepository, times(1)).save(any(Course.class));
//    }
//
//    @Test
//    public void testDeleteCourse_Success() {
//        User admin = new User(1L, "Admin", "admin@example.com", "admin123", Role.Admin);
//        Course course = new Course();
//        course.setId(1L);
//
//        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
//
//        courseService.deleteCourse(1L, admin);
//
//        verify(courseRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void testEnrollStudent_Success() {
//        Course course = new Course();
//        course.setId(1L);
//
//        User student = new User(3L, "Student", "student@example.com", "stud123", Role.Student);
//
//        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
//        when(userRepository.findById(3L)).thenReturn(Optional.of(student));
//        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        courseService.enrollStudent(1L, 3L);
//
//        assertTrue(course.getStudents().contains(student));
//        verify(courseRepository, times(1)).save(course);
//    }
//
//    @Test
//    public void testGetEnrolledStudents_Success() {
//        Course course = new Course();
//        course.setId(1L);
//
//        User student = new User(3L, "Student", "student@example.com", "stud123", Role.Student);
//        course.getStudents().add(student);
//
//        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
//
//        List<User> enrolledStudents = courseService.getEnrolledStudents(1L);
//
//        assertNotNull(enrolledStudents);
//        assertEquals(1, enrolledStudents.size());
//        assertEquals(student, enrolledStudents.get(0));
//        verify(courseRepository, times(1)).findById(1L);
//    }
//}
