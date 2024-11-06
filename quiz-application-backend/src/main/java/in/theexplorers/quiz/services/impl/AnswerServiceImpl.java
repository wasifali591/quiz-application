package in.theexplorers.quiz.services.impl;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.entities.Answer;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.AnswerRepository;
import in.theexplorers.quiz.services.AnswerService;
import in.theexplorers.quiz.utilities.converters.AnswerConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing {@link Answer} entities.
 *
 * <p>This class implements CRUD operations for {@link Answer} entities, including methods
 * for retrieving answers by ID, getting all answers, adding, updating, and inactivating answers.</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerConverter answerConverter;

    /**
     * Constructor to initialize the AnswerServiceImpl with required dependencies.
     *
     * @param answerRepository the repository for accessing {@link Answer} entities
     * @param answerConverter  the converter for transforming between {@link Answer} and {@link AnswerDto}
     */
    public AnswerServiceImpl(AnswerRepository answerRepository, AnswerConverter answerConverter) {
        this.answerRepository = answerRepository;
        this.answerConverter = answerConverter;
    }

    /**
     * Retrieves an answer by its unique ID.
     *
     * @param id the ID of the answer to retrieve
     * @return an {@link Optional} containing the {@link AnswerDto} if found, or empty if not
     */
    @Override
    public Optional<AnswerDto> getById(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        return answer.map(answerConverter::answerToAnswerDto);
    }

    /**
     * Retrieves all answers as a list of {@link AnswerDto}.
     *
     * @return a list of all {@link AnswerDto} objects
     */
    @Override
    public List<AnswerDto> getAll(Boolean isActive) {
        List<Answer> answerList;
        if (Boolean.TRUE.equals(isActive)) {
            answerList = answerRepository.findAllActive();
        } else {
            answerList = answerRepository.findAll();
        }
        if (answerList.isEmpty()) {
            throw new ResourceNotFoundException("No data available!");
        }
        return answerList.stream()
                .map(answerConverter::answerToAnswerDto)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new answer.
     *
     * @param answerDto the {@link AnswerDto} object to be added
     * @return the saved {@link Answer} entity
     */
    @Override
    public AnswerDto add(AnswerDto answerDto) {
        // Convert the AnswerDto to an Answer entity
        Answer answer = answerConverter.answerDtoToAnswer(answerDto);

        // Save the answer entity and convert the saved entity to AnswerDto
        Answer savedAnswer = answerRepository.save(answer);

        // Convert and return the saved Answer entity as AnswerDto
        return answerConverter.answerToAnswerDto(savedAnswer);
    }


    /**
     * Marks an answer as inactive by its unique ID (soft delete).
     *
     * @param id the ID of the answer to deactivate
     */
    @Override
    public void delete(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        answer.ifPresent(a -> {
            a.setIsActive(false); // Mark the answer as inactive instead of deleting it
            answerRepository.save(a); // Save the updated answer
        });
    }

    /**
     * Updates an existing answer.
     *
     * @param id        the ID of the answer to update
     * @param answerDto the {@link AnswerDto} containing updated answer details
     * @return the updated {@link AnswerDto} after the update
     */
    @Override
    public AnswerDto update(Long id, AnswerDto answerDto) {
        Optional<Answer> existingAnswerOpt = answerRepository.findById(id);
        if (existingAnswerOpt.isPresent()) {
            Answer existingAnswer = existingAnswerOpt.get();
            // Map only updated fields from the DTO to the existing entity
            existingAnswer.setSelectedAnswer(answerDto.getSelectedAnswer());
            existingAnswer.setIsActive(answerDto.getIsActive());

            // Save the updated entity back to the repository
            Answer updatedAnswer = answerRepository.save(existingAnswer);

            // Return the updated DTO
            return answerConverter.answerToAnswerDto(updatedAnswer);
        }
        return null; // Or throw an exception based on your business logic
    }
}
