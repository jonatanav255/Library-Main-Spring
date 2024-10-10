package com.example.library;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Author {
       
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                private Long id;
                private String name;

                @OneToMany(mappedBy="author")
                private List<Book> books;

}
