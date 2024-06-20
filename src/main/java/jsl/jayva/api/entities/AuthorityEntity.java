package jsl.jayva.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authority")
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "authority", nullable = false)
    private String authority;

    public AuthorityEntity() {}

    public AuthorityEntity(String authority) {
        this.authority = authority;
    }

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "authorities")
    private Set<UserEntity> userEntities = new LinkedHashSet<>();

    public AuthorityEntity addUserEntity(UserEntity userEntity) {
        userEntity.addAuthority(this);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AuthorityEntity authority1 = (AuthorityEntity) object;
        return Objects.equals(id, authority1.id) && Objects.equals(authority, authority1.authority) && Objects.equals(userEntities, authority1.userEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority, userEntities);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id='" + id + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
