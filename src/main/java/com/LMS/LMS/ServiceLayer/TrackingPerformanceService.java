package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.*;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.AttendanceRepo;
import com.LMS.LMS.RepositoryLayer.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackingPerformanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private AssignmentRepo assignmentRepo;


    public List<Attendance> getAttendanceByStudentAndCourse(User student, Course course) {
        return attendanceRepo.findAll().stream()
                .filter(a -> a.getStudent().equals(student) && a.getLesson().getCourse().equals(course))
                .collect(Collectors.toList());
    }


    public List<Quiz> getQuizScoresByStudentAndCourse(User student, Course course) {
        return quizRepo.findAll().stream()
                .filter(qz -> qz.getStudent().equals(student) && qz.getCourse().equals(course))
                .collect(Collectors.toList());
    }

    public List<Assignment> getAssignmentsByStudentAndCourse(User student, Course course) {
        return assignmentRepo.findAll().stream()
                .filter(a -> a.getStudent().equals(student) && a.getCourse().equals(course))
                .collect(Collectors.toList());
    }
}
