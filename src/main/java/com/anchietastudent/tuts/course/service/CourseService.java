package com.anchietastudent.tuts.course.service;

import com.anchietastudent.tuts.category.model.Category;
import com.anchietastudent.tuts.category.service.CategoryService;
import com.anchietastudent.tuts.course.dto.CourseDTO;
import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.course.repository.CourseRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CategoryService categoryService;

    public List<CourseDTO> findAll() {
        List<Course> courses = repository.findAll();
        List<CourseDTO> dto = courses.stream().map(c -> CourseDTO.buildDTO(c, c.getTeacher()))
                .collect(Collectors.toList());
        return dto;
    }

    public Course save(Course Course) {
        return repository.save(Course);
    }

    public Course findById(UUID id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("object not found"));
    }

    public void delete(Course Course) {
        repository.delete(Course);
    }

    public Long count() {
        return Long.valueOf(repository.count());
    }

    public List<CourseDTO> findAllByCategoryId(UUID categoryId) throws NotFoundException {
        Category category = categoryService.findById(categoryId);
        List<Course> courses = repository.findByCategory(category);
        List<CourseDTO> dto = courses.stream().map(c -> CourseDTO.buildDTO(c, c.getTeacher()))
                .collect(Collectors.toList());
        return dto;
    }
}
