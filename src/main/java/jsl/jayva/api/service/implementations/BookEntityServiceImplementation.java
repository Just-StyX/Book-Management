package jsl.jayva.api.service.implementations;

import jsl.jayva.api.entities.BookEntity;
import jsl.jayva.api.entities.UserEntity;
import jsl.jayva.api.hateoas.BookEntityAssemblerSupport;
import jsl.jayva.api.repository.BookEntityRepository;
import jsl.jayva.api.repository.UserEntityRepository;
import jsl.jayva.api.service.BookEntityService;
import jsl.jayva.model.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static jsl.jayva.api.utility.BookEntityUtility.getBook;
import static jsl.jayva.api.utility.BookEntityUtility.getListOfBooks;

@Service
public class BookEntityServiceImplementation implements BookEntityService {
    private final BookEntityRepository bookEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final BookEntityAssemblerSupport assemblerSupport;

    public BookEntityServiceImplementation(BookEntityRepository bookEntityRepository, UserEntityRepository userEntityRepository,
                                           BookEntityAssemblerSupport assemblerSupport) {
        this.bookEntityRepository = bookEntityRepository;
        this.userEntityRepository = userEntityRepository;
        this.assemblerSupport = assemblerSupport;
    }

    @Override
    public Book createANewBook(Book book) {
        var bookEntity = BookEntity.init()
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .edition(book.getEdition());
        return assemblerSupport.toModel(bookEntityRepository.save(bookEntity));
    }

    @Override
    public void deleteUserBookByUserId(String userId, String bookId) {
        var user = userEntityRepository.findById(userId);
        if (user.isPresent()) {
            var foundUser = user.get();
            Set<BookEntity> userBooks = foundUser
                    .getBookEntities().stream()
                    .filter(bookEntity -> !bookEntity.getId().equals(bookId)).collect(Collectors.toSet());
            foundUser.setBookEntities(userBooks);
            userEntityRepository.save(foundUser);
        }
    }

    @Override
    public List<Book> getAllBooks(int low, int high) {
        Sort.TypedSort<Book> sort = Sort.sort(Book.class);
        Sort orders = sort.by(Book::getAuthor);
        return getListOfBooks(bookEntityRepository.findAll(PageRequest.of(low, high, orders)).getContent());
    }

    @Override
    public List<Book> getAllBooksOwnedByUser(String userId) {
        return getListOfBooks(
                Objects.requireNonNull(userEntityRepository.findById(userId).map(UserEntity::getBookEntities).orElse(null)).stream().toList()
        );
    }

    @Override
    public Book getBookOwnedByUser(String userId, String bookId) {
        return getAllBooksOwnedByUser(userId)
                .stream()
                .filter(bookEntity -> bookEntity.getId().equals(bookId))
                .toList().getFirst();
    }

    @Override
    public Book transferBooksByUserIdAndBookId(String userId, String bookId) {
        var user = userEntityRepository.findById(userId);
        var book = bookEntityRepository.findById(bookId);
        if (user.isPresent() && book.isPresent()) {
            userEntityRepository.save(user.get().addBookEntity(book.get()));
            return getBook(book.get());
        }
        return null;
    }

    @Override
    public Book updateBookById(String bookId, Book book) {
        var bookEntity = bookEntityRepository.findById(bookId).orElse(null);
        if (bookEntity != null) {
            bookEntity = book.getTitle() != null ? bookEntity.title(book.getTitle()) : bookEntity;
            bookEntity = book.getIsbn() != null ? bookEntity.isbn(book.getIsbn()) : bookEntity;
            bookEntity = book.getAuthor() != null ? bookEntity.author(book.getAuthor()) : bookEntity;
            bookEntity = book.getEdition() != null ? bookEntity.edition(book.getEdition()) : bookEntity;
            return getBook(bookEntityRepository.save(bookEntity));
        }
        return null;
    }
}
