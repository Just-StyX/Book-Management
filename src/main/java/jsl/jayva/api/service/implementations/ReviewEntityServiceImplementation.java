package jsl.jayva.api.service.implementations;

import jsl.jayva.api.entities.BookEntity;
import jsl.jayva.api.entities.ReviewEntity;
import jsl.jayva.api.entities.UserEntity;
import jsl.jayva.api.hateoas.ReviewEntityAssemblerSupport;
import jsl.jayva.api.repository.BookEntityRepository;
import jsl.jayva.api.repository.UserEntityRepository;
import jsl.jayva.api.service.ReviewEntityService;
import jsl.jayva.model.Review;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static jsl.jayva.api.utility.BookEntityUtility.getListOfBooks;

@Service
public class ReviewEntityServiceImplementation implements ReviewEntityService {
    private final UserEntityRepository userEntityRepository;
    private final BookEntityRepository bookEntityRepository;
    private final ReviewEntityAssemblerSupport reviewEntityAssemblerSupport;

    public ReviewEntityServiceImplementation(UserEntityRepository userEntityRepository, BookEntityRepository bookEntityRepository,
                                             ReviewEntityAssemblerSupport reviewEntityAssemblerSupport) {
        this.userEntityRepository = userEntityRepository;
        this.bookEntityRepository = bookEntityRepository;
        this.reviewEntityAssemblerSupport = reviewEntityAssemblerSupport;
    }

    @Override
    public Review postReviewByUserId(String userId, String bookId, Review review) {
        var foundBook = getListOfBooks(
                Objects.requireNonNull(userEntityRepository.findById(userId)
                        .map(UserEntity::getBookEntities).orElse(null))
                        .stream().toList()).stream().filter(book -> book.getId().equals(bookId)).toList().stream().findFirst();
        Optional<BookEntity> bookEntity = bookEntityRepository.findById(bookId);
        if (foundBook.isPresent() && bookEntity.isPresent()) {
            var foundBookEntity = bookEntity.get();
            bookEntityRepository.save(
                    foundBookEntity.reviews(ReviewEntity.init().content(review.getContent()).userId(userId).rating(review.getRating()))
            );
            return reviewEntityAssemblerSupport.toModel(review);
        }
        return null;
    }
}
