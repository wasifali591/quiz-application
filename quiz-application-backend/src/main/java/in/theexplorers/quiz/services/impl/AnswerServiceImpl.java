package in.theexplorers.quiz.services.impl;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.entities.Answer;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.AnswerRepository;
import in.theexplorers.quiz.services.AnswerService;
import in.theexplorers.quiz.utilities.StringConstants;
import in.theexplorers.quiz.utilities.converters.AnswerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
     * <p>This method fetches an {@link Answer} entity based on the provided ID from the repository.
     * If found, it maps the entity to an {@link AnswerDto} and returns it.
     *
     * @param id       the ID of the answer to retrieve
     * @param isActive isActive true or false to get active or inactive answer
     * @return an {@link AnswerDto} if the answer is found
     */
    @Override
    public AnswerDto getById(Long id, Boolean isActive) {
        logger.debug(StringConstants.METHOD_START, "getById");

        Optional<Answer> answerOptional;
        if (Boolean.TRUE.equals(isActive)) {
            answerOptional = answerRepository.findActiveById(id);
        } else {
            answerOptional = answerRepository.findById(id);
        }

        Answer answer = answerOptional.orElseThrow(() -> {
            logger.warn(StringConstants.RECORDS_NOT_FOUND);
            return new ResourceNotFoundException(StringConstants.RECORDS_NOT_FOUND);
        });

        AnswerDto answerDto = answerConverter.answerToAnswerDto(answer);
        logger.debug(StringConstants.RECORDS_RETRIEVED, answerDto);

        logger.debug(StringConstants.METHOD_END, "getById");

        return answerDto;
    }


    /**
     * Retrieves all answers as a list of {@link AnswerDto}.
     *
     * @return a list of all {@link AnswerDto} objects
     */
    @Override
    public List<AnswerDto> getAll(Boolean isActive) {
        logger.info(StringConstants.METHOD_START, "getAll");

        // Determine which retrieval method to call based on isActive
        List<Answer> answerList = Boolean.TRUE.equals(isActive)
                ? answerRepository.findAllActive()
                : answerRepository.findAll();

        if (answerList.isEmpty()) {
            logger.warn(StringConstants.RECORDS_NOT_FOUND);
            throw new ResourceNotFoundException(StringConstants.RECORDS_NOT_FOUND);
        }

        logger.debug(StringConstants.RECORDS_RETRIEVED, answerList);
        List<AnswerDto> answerDtos = answerList.stream()
                .map(answerConverter::answerToAnswerDto)
                .collect(Collectors.toList());

        logger.info(StringConstants.METHOD_END, "getAll");
        return answerDtos;
    }


    /**
     * Adds a new answer.
     *
     * @param answerDto the {@link AnswerDto} object to be added
     * @return the saved {@link Answer} entity
     */
    @Override
    public AnswerDto add(AnswerDto answerDto) {
        logger.info(StringConstants.METHOD_START, "add");

        // Convert the AnswerDto to an Answer entity
        Answer answer = answerConverter.answerDtoToAnswer(answerDto);

        // Save the answer entity
        logger.debug(StringConstants.RECORDS_TO_SAVE, answer);
        Answer savedAnswer = answerRepository.save(answer);
        logger.info(StringConstants.RECORDS_SAVED);

        // Convert the saved entity back to AnswerDto and return
        AnswerDto savedAnswerDto = answerConverter.answerToAnswerDto(savedAnswer);

        logger.info(StringConstants.METHOD_END, "add");
        return savedAnswerDto;
    }


    /**
     * Marks an answer as inactive by its unique ID (soft delete).
     *
     * @param id the ID of the answer to deactivate
     */
    @Override
    public void delete(Long id) {
        logger.info(StringConstants.METHOD_START, "delete");

        // Retrieve the answer by ID
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn(StringConstants.RECORDS_NOT_FOUND);
                    return new ResourceNotFoundException(StringConstants.RECORDS_NOT_FOUND);
                });

        // Mark the answer as inactive and save it
        answer.setIsActive(false);
        logger.debug(StringConstants.RECORDS_TO_DELETE, answer);
        answerRepository.save(answer);
        logger.info(StringConstants.RECORDS_DELETED);

        logger.info(StringConstants.METHOD_END, "delete");
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
        logger.info(StringConstants.METHOD_START, "update");

        // Retrieve the answer by ID
        Answer existingAnswer = answerRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn(StringConstants.RECORDS_NOT_FOUND);
                    return new ResourceNotFoundException(StringConstants.RECORDS_NOT_FOUND);
                });

        // Update the existing answer with fields from the DTO
        answerConverter.toUpdateAnswer(answerDto, existingAnswer);

        // Save the updated answer and convert it to DTO
        logger.debug(StringConstants.RECORDS_TO_UPDATE, existingAnswer);
        Answer updatedAnswer = answerRepository.save(existingAnswer);
        logger.info((StringConstants.RECORDS_UPDATED));

        AnswerDto updatedAnswerDto = answerConverter.answerToAnswerDto(updatedAnswer);

        logger.info(StringConstants.METHOD_END, "update");

        return updatedAnswerDto;
    }
}
