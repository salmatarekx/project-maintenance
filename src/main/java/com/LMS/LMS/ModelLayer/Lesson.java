package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ElementCollection
    private List<String> attendanceCodes; // OTPs for attendance

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<String> getAttendanceCodes() {
        return attendanceCodes;
    }

    public void setAttendanceCodes(List<String> attendanceCodes) {
        this.attendanceCodes = attendanceCodes;
    }
}
