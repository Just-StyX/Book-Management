package jsl.jayva.api.repository;

import jsl.jayva.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
}
