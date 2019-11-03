package com.bookstore.mutual.web;

import com.bookstore.mutual.domain.Book;
import com.bookstore.mutual.domain.bookinfos.BookItem;
import com.bookstore.mutual.domain.bookinfos.BookVolumeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;


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
//        logger.info("url is {}", url);// flowers+inauthor:keyes";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book> responseEntity = restTemplate
                .getForEntity(url, Book.class );

        Book book;
        book = responseEntity.getBody();
//        book.getItems().sort(Comparator.comparing(BookItem::getId));
//        log.info("google books api response is {}", book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

//    @GetMapping(value = "/{name}", produces = "application/json")
//    public ResponseEntity<String> getBook(@PathVariable("name") String name) {
//        name = name.replaceAll(" ", "%20");
//        String url = googleBooksApiUrl + "/volumes?q=" + name + "&maxResults=10";
////        log.info("url is {}", url);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate
//                .getForEntity(url, String.class);
//
//        JsonObject book;
//        JsonReader reader = Json.createReader(new StringReader(responseEntity.getBody()));
//        book = reader.readObject();
//
//        JsonArray items = book.getJsonArray("items");
////        log.info("Number of items are {}", items.size());
//
//        for (JsonObject item : items.getValuesAs(JsonObject.class)) {
//            JsonObject volumeInfo = item.getJsonObject("volumeInfo");
//            processVolumeInfo(volumeInfo);
//        }
////        log.info("=====================================");
//        //log.info("Response is {}", responseEntity.getBody());
//        return new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
//    }
//
//    private void processVolumeInfo(JsonObject volumeInfo) {
//        String title = volumeInfo.getString("title");
////        log.info("title is {}", title);
//
//        JsonArray authors = volumeInfo.getJsonArray("authors");
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < authors.size(); i++) {
//            sb.append(authors.getString(i));
//        }
//
////        log.info("authors are {}", sb.toString());
//    }



}
