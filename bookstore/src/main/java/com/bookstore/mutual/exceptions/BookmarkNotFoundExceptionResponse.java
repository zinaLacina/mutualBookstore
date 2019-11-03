package com.bookstore.mutual.exceptions;

public class BookmarkNotFoundExceptionResponse {

    private String bookmarkNotFound;

    public BookmarkNotFoundExceptionResponse(String bookmarkNotFound) {
        bookmarkNotFound = bookmarkNotFound;
    }

    public String getBookmarkNotFound() {
        return bookmarkNotFound;
    }

    public void setBookmarkNotFound(String bookmarkNotFound) {
        this.bookmarkNotFound = bookmarkNotFound;
    }
}
