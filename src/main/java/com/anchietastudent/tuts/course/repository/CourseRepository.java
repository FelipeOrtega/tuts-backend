package com.anchietastudent.tuts.course.repository;

import com.anchietastudent.tuts.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
