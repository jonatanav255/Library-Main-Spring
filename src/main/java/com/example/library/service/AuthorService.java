package com.example.library.service;

import com.example.library.dto.AuthorDTO;
import com.example.library.mapper.EntityToDTOMapper;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(EntityToDTOMapper::toAuthorDTO)
                .collect(Collectors.toList());
    }

    public Optional<AuthorDTO> getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(EntityToDTOMapper::toAuthorDTO);
    }

    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        Author author = EntityToDTOMapper.toAuthorEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return EntityToDTOMapper.toAuthorDTO(savedAuthor);
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDetails) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(authorDetails.getName());
                    Author updatedAuthor = authorRepository.save(author);
                    return EntityToDTOMapper.toAuthorDTO(updatedAuthor);
                }).orElseThrow(() -> new RuntimeException("Author not found with id " + id));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
