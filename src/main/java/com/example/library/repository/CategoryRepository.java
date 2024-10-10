package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
