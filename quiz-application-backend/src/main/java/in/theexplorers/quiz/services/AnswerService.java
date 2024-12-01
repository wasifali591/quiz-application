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

    /**
     * Retrieves an answer by its unique ID.
     *
     * <p>This method fetches an {@link Answer} entity based on the provided ID from the repository.
     * If found, it maps the entity to an {@link AnswerDto} and returns it.
     *
     * @param id       the ID of the answer to retrieve
     * @param isActive isActive true or false to get active or inactive answer
     * @return an {@link AnswerDto} if the answer is found
     */
    AnswerDto getById(Long id, Boolean isActive);

    /**
     * Retrieves all answers in the system.
     *
     * @param isActive true or false to get active or inactive answer
     * @return a list of all answers as {@link AnswerDto} objects
     */
    List<AnswerDto> getAll(Boolean isActive);

    /**
     * Adds a new answer.
     *
     * @param answerDto the data transfer object containing details of the answer to add
     * @return the saved answer as an {@link AnswerDto}
     */
    AnswerDto add(AnswerDto answerDto);

    /**
     * Deletes an answer by its unique ID.
     *
     * @param id the ID of the answer to delete
     */
    void delete(Long id);

    /**
     * Updates an existing answer.
     *
     * @param id        the ID of the answer to update
     * @param answerDto the data transfer object containing updated details of the answer
     * @return the updated answer as an {@link AnswerDto}
     */
    AnswerDto update(Long id, AnswerDto answerDto);
}
