package com.anchietastudent.tuts.course.dto;

import com.anchietastudent.tuts.category.model.Category;
import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.topic.TopicDTO;
import com.anchietastudent.tuts.topic.model.Topic;
import com.anchietastudent.tuts.user.model.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CourseCreateDTO {

    public CourseCreateDTO() { }

    private UUID id;
    private String name;
    private String description;
    private UUID categoryId;
    private UUID teacherId;
    private List<TopicDTO> topics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static Course toCourseEntity(CourseCreateDTO dto, Category category, User teacher, Set<Topic> topics) {
        Course course = new Course();
        course.setCategory(category);
        course.setDescription(dto.getDescription());
        course.setName(dto.getName());
        course.setTeacher(teacher);
        course.setTopics(topics);
        return course;
    }
}
