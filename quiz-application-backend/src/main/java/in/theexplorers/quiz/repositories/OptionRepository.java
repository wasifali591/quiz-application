package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.Option;
import in.theexplorers.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is a repository interface which provides crud operation for {@link Option}.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    /**
     * Finds all options associated with a specific question.
     *
     * @param questionId the ID of the question
     * @return a list of options related to the given question
     */
    @Query("SELECT o.* FROM option o WHERE o.question_id = :questionId")
    List<Option> findByQuestionId(@Param("questionId") Long questionId);

    /**
     * Retrieves all options associated with the given question.
     *
     * @param question The question entity whose options are to be fetched.
     * @return A list of Option entities associated with the specified question.
     */
    List<Option> findByQuestion(Question question);
}
