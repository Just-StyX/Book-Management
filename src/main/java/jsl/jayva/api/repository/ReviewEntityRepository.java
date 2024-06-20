package jsl.jayva.api.repository;

import jsl.jayva.api.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, String> {
}
