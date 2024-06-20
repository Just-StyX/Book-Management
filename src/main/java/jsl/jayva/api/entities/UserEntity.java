package jsl.jayva.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    @Max(1)
    @Min(0)
    private int enabled;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<AuthorityEntity> authorities = new LinkedHashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "userEntities")
    private Set<BookEntity> bookEntities = new LinkedHashSet<>();

    public static UserEntity init() {
        return new UserEntity();
    }

    public UserEntity addAuthority(AuthorityEntity authority) {
        this.authorities.add(authority);
        authority.getUserEntities().add(this);
        return this;
    }

    public UserEntity addBookEntity(BookEntity bookEntity) {
        this.bookEntities.add(bookEntity);
        bookEntity.getUserEntities().add(this);
        return this;
    }

    public UserEntity username(String username) {
        this.username = username;
        return this;
    }

    public UserEntity password(String password) {
        this.password = password;
        return this;
    }

    public UserEntity enabled(int enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Max(1)
    @Min(0)
    public int getEnabled() {
        return enabled;
    }

    public Set<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public Set<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public void setBookEntities(Set<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
