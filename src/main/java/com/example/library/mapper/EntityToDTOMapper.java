package com.example.library.mapper;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.BookDTO;
import com.example.library.dto.CategoryDTO;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Category;

public class EntityToDTOMapper {

    public static AuthorDTO toAuthorDTO(Author author) {
        if (author == null) return null;
        return new AuthorDTO(author.getId(), author.getName());
    }

    public static CategoryDTO toCategoryDTO(Category category) {
        if (category == null) return null;
        return new CategoryDTO(category.getId(), category.getName());
    }

    public static BookDTO toBookDTO(Book book) {
        if (book == null) return null;
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.isAvailable(),
                toAuthorDTO(book.getAuthor()),
                toCategoryDTO(book.getCategory())
        );
    }

    public static Author toAuthorEntity(AuthorDTO authorDTO) {
        if (authorDTO == null) return null;
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return author;
    }

    public static Category toCategoryEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) return null;
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public static Book toBookEntity(BookDTO bookDTO) {
        if (bookDTO == null) return null;
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAvailable(bookDTO.isAvailable());
        book.setAuthor(toAuthorEntity(bookDTO.getAuthor()));
        book.setCategory(toCategoryEntity(bookDTO.getCategory()));
        return book;
    }
}
