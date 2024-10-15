package com.example.library.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class BookTest {

    private Validator validator;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        // Set up Hibernate Validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testBookValid() {
        // Given: A valid book
        Author author = new Author("George Orwell");
        Category category = new Category("Dystopian");
        Book book = new Book("1984", author, category, true);

        // When: Validating the book
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        // Then: There should be no violations
        assertTrue(violations.isEmpty());
    }

    @Test
    void testBookInvalid() {
        // Given: An invalid book (blank title)
        Book book = new Book();
        book.setTitle("");  // Invalid title
        book.setAuthor(new Author("George Orwell"));
        book.setCategory(new Category("Dystopian"));
        book.setAvailable(true);

        // When: Validating the book
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        // Then: There should be a violation because the title is blank
        assertFalse(violations.isEmpty());
        assertEquals("Book title is required", violations.iterator().next().getMessage());
    }

    @Test
    void testGettersAndSetters() {
        // Given: A sample book
        Author author = new Author("George Orwell");
        Category category = new Category("Dystopian");
        Book book = new Book();
        book.setId(1L);
        book.setTitle("1984");
        book.setAvailable(true);
        book.setAuthor(author);
        book.setCategory(category);

        // Then: Verify the fields
        assertEquals(1L, book.getId());
        assertEquals("1984", book.getTitle());
        assertTrue(book.isAvailable());
        assertEquals("George Orwell", book.getAuthor().getName());
        assertEquals("Dystopian", book.getCategory().getName());
    }
}
