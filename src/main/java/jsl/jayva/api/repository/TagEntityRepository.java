package jsl.jayva.api.repository;

import jsl.jayva.api.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagEntityRepository extends JpaRepository<TagEntity, String> {
}
