package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.mapper.EntityToDTOMapper;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Category;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository,
                       CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(EntityToDTOMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(EntityToDTOMapper::toBookDTO);
    }

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = EntityToDTOMapper.toBookEntity(bookDTO);

        // Fetch existing Author and Category from the database
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with id " + book.getAuthor().getId()));
        Category category = categoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found with id " + book.getCategory().getId()));

        // Set the managed entities
        book.setAuthor(author);
        book.setCategory(category);

        Book savedBook = bookRepository.save(book);
        return EntityToDTOMapper.toBookDTO(savedBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAvailable(bookDetails.isAvailable());

                    // Fetch and set the full Author and Category entities
                    Author author = authorRepository.findById(bookDetails.getAuthor().getId())
                            .orElseThrow(() -> new RuntimeException("Author not found with id " + bookDetails.getAuthor().getId()));
                    Category category = categoryRepository.findById(bookDetails.getCategory().getId())
                            .orElseThrow(() -> new RuntimeException("Category not found with id " + bookDetails.getCategory().getId()));

                    book.setAuthor(author);
                    book.setCategory(category);

                    Book updatedBook = bookRepository.save(book);
                    return EntityToDTOMapper.toBookDTO(updatedBook);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
