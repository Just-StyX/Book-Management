package jsl.jayva.api.repository;

import jsl.jayva.api.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String> {
}
