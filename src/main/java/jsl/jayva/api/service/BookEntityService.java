package jsl.jayva.api.service;

import jsl.jayva.model.Book;

import java.util.List;

public interface BookEntityService {
    Book createANewBook(Book book);
    void deleteUserBookByUserId(String userId, String bookId);
    List<Book> getAllBooks(int pageNumber, int size);
    List<Book> getAllBooksOwnedByUser(String userId);
    Book getBookOwnedByUser(String userId, String bookId);
    Book transferBooksByUserIdAndBookId(String userId, String bookId);
    Book updateBookById(String bookId, Book book);
}
