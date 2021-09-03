package com.anchietastudent.tuts.topic;

import com.anchietastudent.tuts.course.Course;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Topic {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
