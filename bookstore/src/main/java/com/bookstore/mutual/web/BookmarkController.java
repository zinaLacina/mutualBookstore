package com.bookstore.mutual.web;

import com.bookstore.mutual.domain.Bookmark;
import com.bookstore.mutual.services.BookmarkService;
import com.bookstore.mutual.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/bookmark")
@CrossOrigin
public class BookmarkController {


    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;




    @PostMapping("")
    public ResponseEntity<?> createNewBookmark(@Valid @RequestBody Bookmark bookmark, BindingResult result, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;
        bookmark.setUserId(principal.getName());
        Bookmark bookmark1 = bookmarkService.createBook(bookmark);
        return new ResponseEntity<Bookmark>(bookmark1, HttpStatus.OK);
    }


    @GetMapping("/{bookmarkId}")
    public ResponseEntity<?> getBookmarkById(@PathVariable String bookmarkId, Principal principal){
        Bookmark bookmark = bookmarkService.getBookById(bookmarkId, principal.getName());

        return new ResponseEntity<Bookmark>(bookmark, HttpStatus.OK);
    }


    @GetMapping("/all")
    public Iterable<Bookmark> getAllBookmarks(Principal principal){
        return bookmarkService.getBooksByUserId(principal.getName());
    }


    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<?> deleteBookmark(@PathVariable String bookmarkId, Principal principal){
        bookmarkService.deleteBookById(bookmarkId, principal.getName());

        return new ResponseEntity<String>("Bookmark with ID: '"+bookmarkId+"' was deleted", HttpStatus.OK);
    }
}
