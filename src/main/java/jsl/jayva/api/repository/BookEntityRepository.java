package jsl.jayva.api.repository;

import jsl.jayva.api.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookEntityRepository extends JpaRepository<BookEntity, String> {
}
