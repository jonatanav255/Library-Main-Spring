package com.example.library.dto;

import org.mockito.Mock;

import jakarta.validation.constraints.NotBlank;

public class AuthorDTO {

    private Long id;

    @NotBlank(message = "Author name is required")
    private String name;
    // Do not include 'books' to prevent recursion

    // Constructors
    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String name) {
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

    @Override
    public String toString() {
        return "Author{id=" + id + ", name='" + name + "'}";
    }

}
