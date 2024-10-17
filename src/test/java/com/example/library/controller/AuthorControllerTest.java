// package com.example.library.controller;

// import java.util.Optional;

// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;

// import com.example.library.dto.AuthorDTO;
// import com.example.library.service.AuthorService;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// @WebMvcTest(AuthorController.class)
// public class AuthorControllerTest {

//     @Autowired
//     private MockMvc mockMvc;  // MockMvc simulates HTTP requests to the controller

//     @MockBean
//     private AuthorService authorService;  // MockBean creates a mock for the AuthorService

//     @Test
//     void testGetAuthorById() throws Exception {
//         // Given: A sample AuthorDTO
//         AuthorDTO authorDTO = new AuthorDTO(1L, "George Orwell");

//         // Mock the service call
//         when(authorService.getAuthorById(1L)).thenReturn(Optional.of(authorDTO));

//         // When: Performing a GET request to "/api/authors/1"
//         mockMvc.perform(get("/api/authors/1"))
//                 .andExpect(status().isOk())  // Expect HTTP 200 status
//                 .andExpect(jsonPath("$.id").value(1L))  // Expect the ID in the response JSON
//                 .andExpect(jsonPath("$.name").value("George Orwell"));  // Expect the name

//         // Verify that the service's getAuthorById() method was called exactly once
//         verify(authorService, times(1)).getAuthorById(1L);
//     }
// }
