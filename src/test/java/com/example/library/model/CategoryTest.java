package com.example.library.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class CategoryTest {

    private Validator validator;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        // Set up Hibernate Validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCategoryValid() {
        // Given: A valid category
        Category category = new Category();
        category.setName("Fiction");

        // When: Validating the category
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        // Then: There should be no violations
        assertTrue(violations.isEmpty());
    }

    @Test
    void testCategoryInvalid() {
        // Given: An invalid category (blank name)
        Category category = new Category();
        category.setName("");

        // When: Validating the category
        Set<ConstraintViolation<Category>> violations = validator.validate(category);


        // Then: There should be a violation because the name is blank
        assertFalse(violations.isEmpty());  // We expect violations to be present
        assertEquals("Category name is required", violations.iterator().next().getMessage());
    }

    @Test
    void testGettersAndSetters() {
        // Given: A sample category
        Category category = new Category();
        category.setId(1L);
        category.setName("Science Fiction");

        // Then: Verify the fields
        assertEquals(1L, category.getId());
        assertEquals("Science Fiction", category.getName());
    }
}
