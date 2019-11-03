package com.bookstore.mutual.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstore.mutual.domain.Bookmark;
import com.bookstore.mutual.exceptions.BookmarkNotFoundException;
import com.bookstore.mutual.repositories.BookmarkRepository;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {


    private BookmarkRepository bookmarkRepository;

    /**
     * @param bookmarkRepository injects
     *  Constructor injection.
     */
    BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Bookmark getBookById(String id, String username) {
        List<Bookmark> listBookmarks = bookmarkRepository.findByUserId(username).get();
        if (listBookmarks == null) throw new BookmarkNotFoundException("You did not bookmark any book");
        Optional<Bookmark> optionalBook = bookmarkRepository.findById(id);
        return optionalBook.orElseThrow(() ->
                new BookmarkNotFoundException("Unable to find the book by id " + id));
    }



    public List<Bookmark> getBooksByUserId(String userId) {
        Optional<List<Bookmark>> bookList = bookmarkRepository.findByUserId(userId);

        return bookList.orElse(new ArrayList<>());
    }

    public Bookmark createBook(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
        return bookmark;
    }



    public void deleteBookById(String id, String username) {
        Bookmark listBookmark = bookmarkRepository.findByUserIdAndId(username,id).get();
        if (listBookmark == null) throw new BookmarkNotFoundException("You did not bookmark any book");
        bookmarkRepository.deleteById(id);
    }

}
