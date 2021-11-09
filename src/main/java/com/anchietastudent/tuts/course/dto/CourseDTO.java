package com.anchietastudent.tuts.course.dto;

import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.user.model.User;

import java.util.UUID;

public class CourseDTO {

    public CourseDTO() { }

    private UUID courseId;

    private String name;

    private String teacherName;

    private String description;

    private String teacherId;

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

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public static CourseDTO buildDTO(Course course, User teacher) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setTeacherName(teacher.getName());
        courseDTO.setTeacherId(teacher.getId().toString());
        return courseDTO;
    }
}
