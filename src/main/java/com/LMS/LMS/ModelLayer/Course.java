package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String duration;



//    @ElementCollection
//    private List<String> mediaFiles; // URLs or paths to course materials

    @ManyToOne
    private User instructor;

    @OneToMany
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany
    private List<Assignment> assignments = new ArrayList<>();

    @JoinColumn(name = "instructor_id", nullable = false)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

//    public List<String> getMediaFiles() {
//        return mediaFiles;
//    }
//
//    public void setMediaFiles(List<String> mediaFiles) {
//        this.mediaFiles = mediaFiles;
//    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
