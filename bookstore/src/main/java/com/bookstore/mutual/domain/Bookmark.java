package com.bookstore.mutual.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "bookmarks")
public class Bookmark {
    @Id
    String id;

    String name;
    String author;
    String userId;
    String isbn;
    String genre;
    String year;
    String url;
    int bookRating;
    int count;
    @DBRef
    List<Note> bookNote;
}
