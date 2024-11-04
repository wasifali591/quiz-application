package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.Answer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface which provides CRUD operations for {@link Answer}.
 *
 * <p>This interface allows for the management of user answers in the quiz application,
 * enabling basic CRUD operations to interact with the underlying database.</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
@Tag(name = "Answer Repository", description = "Repository for managing user answers to quiz questions.")
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
