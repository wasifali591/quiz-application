package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.Answer;
import in.theexplorers.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides CRUD operations for {@link Answer}.
 *
 * <p>This interface allows for the management of user answers in the quiz application,
 * enabling basic CRUD operations to interact with the underlying database.</p>
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    /**
     * Find all active {@link Answer} entity.
     *
     * @return list of business.
     */
    @Query("SELECT a from Answer a where a.isActive=true")
    List<Answer> findAllActive();

    /**
     * Find active {@link Answer} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT a from Answer a where a.id=:id AND a.isActive=true")
    Optional<Answer> findActiveById(@Param("id") Long id);

    /**
     * Finds all answers submitted by a specific user for a specific quiz.
     *
     * @param userId the ID of the user
     * @param quizId the ID of the quiz
     * @return a list of answers matching the given user and quiz IDs
     */
    @Query("SELECT a FROM answer a JOIN question q ON a.question_id = q.id WHERE a.user_id = :userId AND q.quiz_id = :quizId")
    List<Answer> findByUserIdAndQuizId(@Param("userId") Long userId, @Param("quizId") Long quizId);

    /**
     * Retrieves a list of answers associated with the specified question.
     *
     * @param question the question entity for which the answers are being retrieved.
     * @return a list of {@link Answer} entities associated with the provided question.
     */
    @Query("SELECT a FROM Answer a WHERE a.question = :question")
    List<Answer> findByQuestion(@Param("question") Question question);

}
