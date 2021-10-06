package com.anchietastudent.tuts.course.model;

import com.anchietastudent.tuts.category.model.Category;
import com.anchietastudent.tuts.topic.model.Topic;
import com.anchietastudent.tuts.user.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Course {

    public Course() {
    }

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
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
    private List<User> students;

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

    public List<User> getStudents() {
        return students;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
