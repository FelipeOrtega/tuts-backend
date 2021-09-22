package com.anchietastudent.tuts.course.controller;

import com.anchietastudent.tuts.course.dto.CourseDTO;
import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.course.service.CourseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/course")
    public ResponseEntity<List<CourseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/category/{id}/course")
    public ResponseEntity<List<CourseDTO>> findAllByCategoryId(@PathVariable("id") UUID categoryId) throws NotFoundException{
        return ResponseEntity.ok(service.findAllByCategoryId(categoryId));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> findOne(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/course")
    public ResponseEntity<Course> save(@RequestBody Course created){
        return ResponseEntity.ok(service.save(created));
    }

    @PutMapping("/course")
    public ResponseEntity<Course> update(@RequestBody Course updated){
        return ResponseEntity.ok(service.save(updated));
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws NotFoundException {
        service.delete(service.findById(id));
        return ResponseEntity.ok("Deleted object with id: ".concat(id.toString()));
    }

    @GetMapping("/course/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }
}
