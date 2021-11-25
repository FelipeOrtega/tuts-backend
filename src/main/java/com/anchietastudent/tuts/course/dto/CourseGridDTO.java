package com.anchietastudent.tuts.course.dto;

import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.topic.TopicDTO;

import java.util.List;
import java.util.UUID;

public class CourseGridDTO {

    public CourseGridDTO() { }

    private UUID id;

    private String name;

    private String teacherName;

    private String description;

    private String categoryName;

    private List<TopicDTO> topics;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }

    public static CourseGridDTO toDTO(Course course) {
        CourseGridDTO dto = new CourseGridDTO();
        dto.setCategoryName(course.getCategory().getName());
        dto.setDescription(course.getDescription());
        dto.setTeacherName(course.getTeacher().getName());
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setTopics(TopicDTO.toDTOs(course.getTopics()));
        return dto;
    }
}
