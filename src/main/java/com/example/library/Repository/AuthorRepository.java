package com.example.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.model.Author;

public interface  AuthorRepository extends JpaRepository<Author, Long>{
                
}
