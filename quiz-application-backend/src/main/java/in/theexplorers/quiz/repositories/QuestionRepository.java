package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is a repository interface which provides crud operation for {@link Question}.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    /**
     * Fetch all questions associated with the given quiz ID using a native query.
     *
     * @param quizId The ID of the quiz.
     * @return A list of questions.
     */
    @Query("SELECT q FROM Question q WHERE q.quiz.id = :quizId")
    List<Question> findByQuizId(@Param("quizId") Long quizId);

    @Modifying
    @Query("UPDATE Question q SET q.isActive = false WHERE q.id = :questionId")
    void deleteById(@Param("questionId") Long questionId);

}
