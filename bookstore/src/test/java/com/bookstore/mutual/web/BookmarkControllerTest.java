package com.bookstore.mutual.web;

import com.bookstore.mutual.BackendApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 *  Integration Test of my BookmarkController
 */
@SpringBootTest(classes = BackendApplication.class)
class BookmarkControllerTest {


    @InjectMocks
    private BookmarkController bookmarkController;


    private MockMvc restBookmarkMockMvc;



    @BeforeEach
    void setUp() throws Exception {
//        To avoid side effect between tests
        MockitoAnnotations.initMocks(this);
        restBookmarkMockMvc = MockMvcBuilders.standaloneSetup(bookmarkController).build();

    }



    @Test
    public void getBookmarkById() throws Exception{
    }

    @Test
    void getAllBookmarks() {
    }

    @Test
    void deleteBookmark() {
    }
}