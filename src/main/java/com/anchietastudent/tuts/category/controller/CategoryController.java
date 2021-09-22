package com.anchietastudent.tuts.category.controller;

import com.anchietastudent.tuts.category.model.Category;
import com.anchietastudent.tuts.category.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("")
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findOne(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Category> save(@RequestBody Category created){
        return ResponseEntity.ok(service.save(created));
    }

    @PutMapping("")
    public ResponseEntity<Category> update(@RequestBody Category updated){
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
