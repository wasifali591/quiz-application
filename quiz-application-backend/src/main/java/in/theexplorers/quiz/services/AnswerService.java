package in.theexplorers.quiz.services;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.entities.Answer;

import java.util.List;

/**
 * Service interface for managing {@link Answer} entities in the quiz application.
 *
 * <p>This interface provides CRUD operations for answers, including methods to retrieve,
 * add, delete, and update answers.</p>
 *
 * <p>All methods either return data in the form of {@link AnswerDto} for client use,
 * or handle specific tasks such as deleting an answer by ID.</p>
 *
 * <p>Usage examples:</p>
 * <pre>
 *     answerService.add(new AnswerDto(...));
 *     Optional&lt;AnswerDto&gt; answer = answerService.getById(1L);
 * </pre>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AnswerService {
    AnswerDto submitAnswer(AnswerDto answerDto);

    List<AnswerDto> getAnswersByUserIdAndQuizId(Long userId, Long quizId);
}
