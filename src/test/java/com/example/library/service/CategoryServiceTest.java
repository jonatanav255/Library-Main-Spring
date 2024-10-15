package com.example.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.library.dto.CategoryDTO;
import com.example.library.model.Category;
import com.example.library.repository.CategoryRepository;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory() {

        // Given: A sample CategoryDTO
        CategoryDTO categoryDTO = new CategoryDTO(null, "Science Fiction");

        // And: A mock Category entity to return when saving
        Category mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Science Fiction");

        // When: the repository's save method is called
        when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

        // Act: Call the service's addCategory method
        CategoryDTO result = categoryService.addCategory(categoryDTO);

        // Then: Assert that the returned CategoryDTO matches the expected result
        assertNotNull(result.getId()); // ID should be set
        assertEquals("Science Fiction", result.getName()); // Name should match

        // Verify that the repository's save method was called exactly once
        verify(categoryRepository, times(1)).save(any(Category.class));
    }
}
