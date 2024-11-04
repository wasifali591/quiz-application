package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.Question;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface which provides crud operation for {@link Question}.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
@Tag(name = "Question Repository", description = "Repository for managing questions of the quiz.")
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
