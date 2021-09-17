package com.anchietastudent.tuts.category.service;

import com.anchietastudent.tuts.category.repository.CategoryRepository;
import com.anchietastudent.tuts.category.model.Category;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category findById(UUID id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("object not found"));
    }

    public void delete(Category category) {
        repository.delete(category);
    }

    public Long count() {
        return Long.valueOf(repository.count());
    }

}
