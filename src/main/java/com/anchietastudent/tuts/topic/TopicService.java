package com.anchietastudent.tuts.topic;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    public List<Topic> findAll() {
        return repository.findAll();
    }

    public Topic save(Topic Topic) {
        return repository.save(Topic);
    }

    public Topic findById(UUID id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("object not found"));
    }

    public void delete(Topic Topic) {
        repository.delete(Topic);
    }

    public Long count() {
        return Long.valueOf(repository.count());
    }

}
