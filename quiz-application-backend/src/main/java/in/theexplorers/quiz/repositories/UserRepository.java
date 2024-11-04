package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface which provides crud operation for {@link User}.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
@Tag(name = "User Repository", description = "Repository for managing user.")
public interface UserRepository extends JpaRepository<User, Long> {
}
