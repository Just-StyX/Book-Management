package jsl.jayva.api.controller;

import jakarta.validation.Valid;
import jsl.jayva.api.BookApi;
import jsl.jayva.api.service.BookEntityService;
import jsl.jayva.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BookApi {

    private final BookEntityService bookEntityService;

    public BookController(BookEntityService bookEntityService) {
        this.bookEntityService = bookEntityService;
    }

    @Override
    public ResponseEntity<Book> createANewBook(@Valid Book book) {
        return ResponseEntity.ok(bookEntityService.createANewBook(book));
    }

    @Override
    public ResponseEntity<Void> deleteByUserId(String userId, String bookId) {
        bookEntityService.deleteUserBookByUserId(userId, bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<Book>> getAllBooks(Integer pageNumber, Integer size) {
        return ResponseEntity.ok(bookEntityService.getAllBooks(pageNumber, size));
    }

    @Override
    public ResponseEntity<List<Book>> getBooksByUserId(String userId) {
        return ResponseEntity.ok(bookEntityService.getAllBooksOwnedByUser(userId));
    }

    @Override
    public ResponseEntity<Book> getBooksByUserIdAndBookId(String userId, String bookId) {
        return ResponseEntity.ok(bookEntityService.getBookOwnedByUser(userId, bookId));
    }

    @Override
    public ResponseEntity<Book> transferBooksByUserIdAndBookId(String userId, String bookId) {
        var book = bookEntityService.transferBooksByUserIdAndBookId(userId, bookId);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<Book> updateBookById(String bookId, @Valid Book book) {
        return ResponseEntity.ok(bookEntityService.updateBookById(bookId, book));
    }
}
