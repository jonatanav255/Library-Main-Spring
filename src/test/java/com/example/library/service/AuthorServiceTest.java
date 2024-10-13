package com.example.library.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.library.dto.AuthorDTO;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAuthor() {
        // Given: A sample AuthorDTO
        AuthorDTO authorDTO = new AuthorDTO(null, "George Orwell");

        // And: A mock Author entity to return when saving
        Author mockAuthor = new Author();
        mockAuthor.setId(1L);
        mockAuthor.setName("George Orwell");

        // When: the repository's save method is called
        when(authorRepository.save(any(Author.class))).thenReturn(mockAuthor);

        // Act: Call the service's addAuthor method
        AuthorDTO result = authorService.addAuthor(authorDTO);

        // Then: Assert that the returned AuthorDTO matches the expected result
        assertNotNull(result.getId()); // ID should be set
        assertEquals("George Orwell", result.getName()); // Name should match

        // Verify that the repository's save method was called exactly once
        verify(authorRepository, times(1)).save(any(Author.class));
    }
}