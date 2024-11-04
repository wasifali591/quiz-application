package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.Option;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface which provides crud operation for {@link Option}.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
@Tag(name = "Option Repository", description = "Repository for managing answer options for the quiz questions.")
public interface OptionRepository extends JpaRepository<Option, Long> {
}
