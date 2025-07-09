package in.theexplorers.quiz.repositories;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This is a repository interface which provides crud operation for {@link Quiz}.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    /**
     * Finds all quizzes that have a start time before now, an end time after now,
     * and are currently inactive.
     *
     * @param time the current time
     * @return a list of inactive quizzes that are within the active time range
     */
    @Query("SELECT q FROM Quiz q WHERE q.startTime <= :time AND q.endTime >= :time AND q.isActive = false")
    List<Quiz> findAllByStartTimeBeforeAndEndTimeAfterAndIsActiveFalse(@Param("time") LocalDateTime time);
}
