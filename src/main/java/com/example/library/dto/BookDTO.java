package com.example.library.dto;

public class BookDTO {
    private Long id;
    private String title;
    private boolean available;
    private AuthorDTO author;
    private CategoryDTO category;

    // Constructors
    public BookDTO() {}

    public BookDTO(Long id, String title, boolean available, AuthorDTO author, CategoryDTO category) {
        this.id = id;
        this.title = title;
        this.available = available;
        this.author = author;
        this.category = category;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
