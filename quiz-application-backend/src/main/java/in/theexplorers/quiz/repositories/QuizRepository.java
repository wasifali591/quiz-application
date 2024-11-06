package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface which provides crud operation for {@link Quiz}.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
