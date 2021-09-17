package com.anchietastudent.tuts.course.controller;

import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.course.service.CourseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("")
    public ResponseEntity<List<Course>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findOne(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Course> save(@RequestBody Course created){
        return ResponseEntity.ok(service.save(created));
    }

    @PutMapping("")
    public ResponseEntity<Course> update(@RequestBody Course updated){
        return ResponseEntity.ok(service.save(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws NotFoundException {
        service.delete(service.findById(id));
        return ResponseEntity.ok("Deleted object with id: ".concat(id.toString()));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }
}
