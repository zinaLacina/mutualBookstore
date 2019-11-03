package com.bookstore.mutual.web;

import com.bookstore.mutual.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/books")
@CrossOrigin
@Slf4j
public class BookController {

    private final String googleBooksApiUrl = "https://www.googleapis.com/books/v1/";


    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<Book> getBooks(@PathVariable("name") String name) {
        name = name.replaceAll(" ", "%20");
        String url = googleBooksApiUrl + "/volumes?q=" + name + "&maxResults=20";
        log.info("url is {}", url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book> responseEntity = restTemplate
                .getForEntity(url, Book.class );

        Book book;
        book = responseEntity.getBody();
        log.info("google books api response is {}", book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }




}
