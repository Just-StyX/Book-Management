package jsl.jayva.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private String id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "username", nullable = false)
    private String userId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farmer_id")
    private BookEntity bookEntity;

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public static ReviewEntity init() {
        return new ReviewEntity();
    }

    public ReviewEntity content(String content) {
        this.content = content;
        return this;
    }

    public ReviewEntity userId(String userId) {
        this.userId = userId;
        return this;
    }

    public ReviewEntity rating(int rating) {
        this.rating = rating;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public int getRating() {
        return rating;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ReviewEntity that = (ReviewEntity) object;
        return rating == that.rating && Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(userId, that.userId) && Objects.equals(bookEntity, that.bookEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, userId, rating, bookEntity);
    }

    @Override
    public String toString() {
        return "ReviewEntity{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", rating=" + rating +
                '}';
    }
}
