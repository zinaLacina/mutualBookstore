package com.bookstore.mutual.web;

import com.bookstore.mutual.BackendApplication;
import com.bookstore.mutual.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *  BookController Unit Test
 */
@SpringBootTest(classes = BackendApplication.class)
class BookControllerTest {

    MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    BookController bookController;


    /**
     * List of samples books
     */
    private Book book;


    @BeforeEach
    void setUp() throws Exception{
        // To avoid side effect between tests
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.bookController).build();

        Book book1 = Book.builder()
                .totalItems(23)
                .build();
        book = new Book();
        book = book1;
    }


    @Test
    void searchBooks() throws Exception{
        mockMvc.perform(get("/api/books/sprin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalItems").exists());
    }
}