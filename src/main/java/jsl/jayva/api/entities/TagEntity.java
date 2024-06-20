package jsl.jayva.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    public TagEntity() {}

    public TagEntity(String tagName) {
        this.tagName = tagName;
    }

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "tags")
    private Set<BookEntity> bookEntities = new LinkedHashSet<>();

    public TagEntity addUserEntity(BookEntity bookEntity) {
        bookEntity.addTags(this);
        return this;
    }


    public String getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public Set<BookEntity> getBookEntities() {
        return bookEntities;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TagEntity tagEntity = (TagEntity) object;
        return Objects.equals(id, tagEntity.id) && Objects.equals(tagName, tagEntity.tagName) && Objects.equals(bookEntities, tagEntity.bookEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName, bookEntities);
    }

    @Override
    public String toString() {
        return "TagEntity{" +
                "id='" + id + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
