package jsl.jayva.api.utility;

import jsl.jayva.api.entities.BookEntity;
import jsl.jayva.model.Book;
import jsl.jayva.model.Review;

import java.util.List;

public class BookEntityUtility {
    public static List<Book> getListOfBooks(List<BookEntity> bookEntities) {
        return bookEntities
                .stream()
                .map(bookEntity -> {
                    var reviews = bookEntity.getReviews().stream().map(reviewEntity -> {
                        var newReview = new Review();
                        return newReview.id(reviewEntity.getId())
                                .content(reviewEntity.getContent())
                                .username(reviewEntity.getUserId())
                                .rating(reviewEntity.getRating());
                    }).toList();
                    var book = new Book();
                    return book.id(bookEntity.getId())
                            .title(bookEntity.getTitle())
                            .isbn(bookEntity.getIsbn())
                            .author(bookEntity.getAuthor())
                            .edition(bookEntity.getEdition())
                            .reviews(reviews);
                }).toList();
    }

    public static Book getBook(BookEntity bookEntity) {
        var reviews = bookEntity.getReviews().stream().map(reviewEntity -> {
            var newReview = new Review();
            return newReview.id(reviewEntity.getId())
                    .content(reviewEntity.getContent())
                    .username(reviewEntity.getUserId())
                    .rating(reviewEntity.getRating());
        }).toList();

        var book = new Book();
        return book.id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .isbn(bookEntity.getIsbn())
                .author(bookEntity.getAuthor())
                .edition(bookEntity.getEdition())
                .reviews(reviews);
    }
}
