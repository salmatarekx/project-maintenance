package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.*;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.AttendanceRepo;
import com.LMS.LMS.RepositoryLayer.QuizGradesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrackingPerformanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private QuizGradesRepo quizGradesRepo;

    @Autowired
    private AssignmentGradesRepo assignmentGradesRepo;


    public Map<String, Object> getSimplifiedPerformance(User student, Course course) {
        Map<String, Object> performanceData = new HashMap<>();

        // Attendance summary
        List<Attendance> attendanceRecords = attendanceRepo.findAll().stream()
                .filter(a -> a.getStudent().equals(student) && a.getLesson().getCourse().equals(course))
                .toList();
        long attendedSessions = attendanceRecords.stream().filter(Attendance::isAttend).count();
        long totalSessions = attendanceRecords.size();
        performanceData.put("attendance", String.format("%d/%d sessions", attendedSessions, totalSessions));

        // Quiz scores summary
        List<Map<String, String>> quizScores = quizGradesRepo.findAll().stream()
                .filter(qs -> qs.getStudent().equals(student) && qs.getQuiz().getCourse().equals(course))
                .map(qs -> Map.of ("quizTitle", qs.getQuiz().getTitle(), "Grades", qs.getGrades()))
                .collect(Collectors.toList());
        performanceData.put("quizScores", quizScores);

        // Assignments summary
        List<Map<String, String>> assignments = assignmentGradesRepo.findAll().stream()
                .filter(a -> a.getStudent().equals(student) && a.getAssignment().getCourse().equals(course))
                .map(a -> Map.of(
                        "assignmentTitle", a.getAssignment().getTitle(),
                        "grade", a.getGrade(),
                        "feedback", a.getFeedback()))
                .collect(Collectors.toList());
        performanceData.put("assignments", assignments);

        return performanceData;
    }
}
