package com.anchietastudent.tuts.course.controller;

import com.anchietastudent.tuts.course.dto.CourseCreateDTO;
import com.anchietastudent.tuts.course.dto.CourseDTO;
import com.anchietastudent.tuts.course.dto.CourseFilterDTO;
import com.anchietastudent.tuts.course.dto.CourseGridDTO;
import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.course.service.CourseService;
import com.anchietastudent.tuts.util.dto.MessageResponseDTO;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    public ResponseEntity<CourseGridDTO> findOne(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(service.findCourse(id));
    }

    @GetMapping("/course/filter")
    public ResponseEntity<List<CourseDTO>> findByFilter(CourseFilterDTO courseFilterDTO) throws NotFoundException{
        return ResponseEntity.ok(service.findByFilter(courseFilterDTO));
    }

    @PostMapping("/course")
    public ResponseEntity<MessageResponseDTO> save(@RequestBody CourseCreateDTO created) throws NotFoundException {
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

    @PutMapping("/course/{id}/enroll/{userId}")
    public ResponseEntity<MessageResponseDTO> enrollStudent(@PathVariable("id") UUID courseId, @PathVariable("id") UUID userId) throws NotFoundException {
        return ResponseEntity.ok(service.enrollStudent(courseId, userId));
    }

    @PostMapping(value = "/course/{id}/image/upload")
    public ResponseEntity<MessageResponseDTO> uploadImage(@RequestParam("imageFile") MultipartFile file,
                                                          @PathVariable("id") UUID courseId) throws BadHttpRequest, NotFoundException {
        return ResponseEntity.ok(service.uploadImage(file, courseId));
    }

    @GetMapping(value = "/course/{id}/image/download")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("id") UUID courseId) throws NotFoundException {
        return service.downloadImage(courseId);
    }
}
