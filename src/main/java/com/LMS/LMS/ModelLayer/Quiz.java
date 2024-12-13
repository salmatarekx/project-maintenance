package com.LMS.LMS.ModelLayer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;




    @ManyToOne
    private Course course;



    public Quiz() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public Quiz(Integer id, String title, Course course ) {
        this.id = id;
        this.title = title;
        this.course = course;

    }}


