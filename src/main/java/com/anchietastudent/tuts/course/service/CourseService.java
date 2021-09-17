package com.anchietastudent.tuts.course.service;

import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.course.repository.CourseRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> findAll() {
        return repository.findAll();
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

}
