// package com.example.library.integration;

// import com.example.library.LibraryApplication;
// import com.example.library.model.Author;
// import com.example.library.repository.AuthorRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.transaction.annotation.Transactional;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.hamcrest.Matchers.is;

// @SpringBootTest(classes = LibraryApplication.class)
// @AutoConfigureMockMvc
// @Transactional
// public class AuthorControllerIntegrationTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private AuthorRepository authorRepository;

//     @SuppressWarnings("unused")
//     @BeforeEach
//     void setUp() {
//         authorRepository.deleteAll();  // Clean the database before each test
//     }

//     @Test
//     void testCreateAndRetrieveAuthor() throws Exception {
//         mockMvc.perform(post("/api/authors")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"name\": \"George Orwell\"}"))
//                 .andExpect(status().isCreated());

//         mockMvc.perform(get("/api/authors"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].name", is("George Orwell")));
//     }

//     @Test
//     void testGetAuthorById() throws Exception {
//         Author author = new Author("George Orwell");
//         authorRepository.save(author);

//         mockMvc.perform(get("/api/authors/" + author.getId()))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.name", is("George Orwell")));
//     }
// }
