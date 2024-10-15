package com.example.library.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.library.dto.BookDTO;
import com.example.library.dto.AuthorDTO;
import com.example.library.dto.CategoryDTO;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Category;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private BookService bookService;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        // Given: A sample BookDTO
        AuthorDTO authorDTO = new AuthorDTO(1L, "George Orwell");
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Dystopian");
        BookDTO bookDTO = new BookDTO(null, "1984", true, authorDTO, categoryDTO);

        // And: A mock Book entity to return when saving
        Book mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("1984");
        mockBook.setAvailable(true);

        // Mocking author and category entities
        Author mockAuthor = new Author();
        mockAuthor.setId(1L);
        mockAuthor.setName("George Orwell");

        Category mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Dystopian");

        // When: the repository's save method is called
        when(authorRepository.findById(1L)).thenReturn(java.util.Optional.of(mockAuthor));
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(mockCategory));
        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        // Act: Call the service's addBook method
        BookDTO result = bookService.addBook(bookDTO);

        // Then: Assert that the returned BookDTO matches the expected result
        assertNotNull(result.getId()); // ID should be set
        assertEquals("1984", result.getTitle()); // Title should match
        assertTrue(result.isAvailable()); // Availability should match

        // Verify that the repository's save method was called exactly once
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}
