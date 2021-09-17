package com.anchietastudent.tuts.topic.controller;

import com.anchietastudent.tuts.topic.service.TopicService;
import com.anchietastudent.tuts.topic.model.Topic;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topic")
public class TopicController  {

    @Autowired
    private TopicService service;

    @GetMapping("")
    public ResponseEntity<List<Topic>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> findOne(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Topic> save(@RequestBody Topic created){
        return ResponseEntity.ok(service.save(created));
    }

    @PutMapping("")
    public ResponseEntity<Topic> update(@RequestBody Topic updated){
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
