package jsl.jayva.api.hateoas;

import jsl.jayva.api.controller.BookController;
import jsl.jayva.api.entities.BookEntity;
import jsl.jayva.model.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static jsl.jayva.api.utility.BookEntityUtility.getBook;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookEntityAssemblerSupport extends RepresentationModelAssemblerSupport<BookEntity, Book> {
    public BookEntityAssemblerSupport() {
        super(BookController.class, Book.class);
    }

    @Override
    public Book toModel(BookEntity bookEntity) {
        Book book = getBook(bookEntity);
        BeanUtils.copyProperties(bookEntity, book);
        book.add(linkTo(methodOn(BookController.class).createANewBook(book)).withSelfRel());
        book.add(linkTo(methodOn(BookController.class).getAllBooks(0, 10)).withRel("all"));
        book.add(linkTo(methodOn(BookController.class).updateBookById(book.getId(), book)).withRel("update-book"));
        return book;
    }
}

