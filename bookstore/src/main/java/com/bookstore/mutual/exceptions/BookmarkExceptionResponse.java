package com.bookstore.mutual.exceptions;

public class BookmarkExceptionResponse {

    private String bookmarkMessage;

    public BookmarkExceptionResponse(String bookmarkMessage) {
        this.bookmarkMessage = bookmarkMessage;
    }

    public String getBookmarkMessage() {
        return bookmarkMessage;
    }

    public void setBookmarkMessage(String bookmarkMessage) {
        this.bookmarkMessage = bookmarkMessage;
    }
}