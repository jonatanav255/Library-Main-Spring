package com.example.library.dto;

public class CategoryDTO {
    private Long id;
    private String name;
    // Do not include 'books' to prevent recursion

    // Constructors
    public CategoryDTO() {}

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
