package com.example.library.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class AuthorTest {

    private Validator validator;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        // Set up Hibernate Validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAuthorValid() {
        // Given: A valid author
        Author author = new Author();
        author.setName("George Orwell");

        // When: Validating the author
        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        // Then: There should be no violations
        assertTrue(violations.isEmpty());
    }

    @Test
    void testAuthorInvalid() {
        // Given: An invalid author (blank name)
        Author author = new Author();
        author.setName("");

        // When: Validating the author
        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        assertFalse(violations.isEmpty());
        assertEquals("Author name is required", violations.iterator().next().getMessage());
    }

    @Test
    void testGettersAndSetters() {
        // Given: A sample author
        Author author = new Author();
        author.setId(1L);
        author.setName("George Orwell");

        // Then: Verify the fields
        assertEquals(1L, author.getId());
        assertEquals("George Orwell", author.getName());
    }
}
