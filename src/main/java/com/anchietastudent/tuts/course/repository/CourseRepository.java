package com.anchietastudent.tuts.course.repository;

import com.anchietastudent.tuts.category.model.Category;
import com.anchietastudent.tuts.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findByCategory(Category category);
}
