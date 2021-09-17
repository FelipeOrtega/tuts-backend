package com.anchietastudent.tuts.category.repository;

import com.anchietastudent.tuts.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
