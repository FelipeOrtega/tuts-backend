package com.anchietastudent.tuts.course.model;

import com.anchietastudent.tuts.category.model.Category;
import com.anchietastudent.tuts.topic.model.Topic;
import com.anchietastudent.tuts.user.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    private String description;

    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private List<User> courseStudents;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topic> topics;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getTeacher() {
        return teacher;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }

    public Category getCategory() {
        return category;
    }

    public List<User> getCourseStudents() {
        return courseStudents;
    }
}
