package jsl.jayva.api.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "edition", nullable = false)
    private String edition;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true,
            mappedBy = "bookEntity", fetch = FetchType.EAGER)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_tags",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<UserEntity> userEntities = new LinkedHashSet<>();

    public static BookEntity init() {
        return new BookEntity();
    }

    public BookEntity addTags(TagEntity tagEntity) {
        this.tags.add(tagEntity);
        tagEntity.getBookEntities().add(this);
        return this;
    }

    public BookEntity addUserEntity(UserEntity userEntity) {
        this.userEntities.add(userEntity);
        userEntity.addBookEntity(this);
        return this;
    }

    public BookEntity title(String title) {
        this.title = title;
        return this;
    }

    public BookEntity isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookEntity author(String author) {
        this.author = author;
        return this;
    }

    public BookEntity edition(String edition) {
        this.edition = edition;
        return this;
    }

    public BookEntity reviews(ReviewEntity reviewEntity) {
        this.reviews.add(reviewEntity);
        reviewEntity.setBookEntity(this);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public String getId() {
        return id;
    }

    public Set<TagEntity> getTags() {
        return tags;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookEntity that = (BookEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(isbn, that.isbn) && Objects.equals(author, that.author) && Objects.equals(edition, that.edition) && Objects.equals(reviews, that.reviews) && Objects.equals(tags, that.tags) && Objects.equals(userEntities, that.userEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, author, edition, reviews, tags, userEntities);
    }
}
