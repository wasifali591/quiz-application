package in.theexplorers.quiz.services.impl;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.entities.Answer;
import in.theexplorers.quiz.entities.Question;
import in.theexplorers.quiz.entities.User;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.AnswerRepository;
import in.theexplorers.quiz.repositories.QuestionRepository;
import in.theexplorers.quiz.repositories.UserRepository;
import in.theexplorers.quiz.services.AnswerService;
import in.theexplorers.quiz.utilities.converters.AnswerConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing {@link Answer} entities.
 *
 * <p>This class implements CRUD operations for {@link Answer} entities, including methods
 * for retrieving answers by ID, getting all answers, adding, updating, and inactivating answers.</p>
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@Slf4j
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerConverter answerConverter;

    public AnswerServiceImpl(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository, AnswerConverter answerConverter) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerConverter = answerConverter;
    }

    @Override
    public AnswerDto submitAnswer(AnswerDto answerDto) {
        User user = userRepository.findById(answerDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Question question = questionRepository.findById(answerDto.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        Answer answer = answerConverter.answerDtoToAnswer(answerDto);
        answer.setUser(user);
        answer.setQuestion(question);
        answer = answerRepository.save(answer);

        return answerConverter.answerToAnswerDto(answer);
    }

    @Override
    public List<AnswerDto> getAnswersByUserIdAndQuizId(Long userId, Long quizId) {
        return answerRepository.findByUserIdAndQuizId(userId, quizId).stream()
                .map(answerConverter::answerToAnswerDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all answers from the database.
     *
     * @return A list of AnswerDto objects representing all answers.
     * @throws ResourceNotFoundException if no answers are found in the database.
     */
    @Override
    public List<AnswerDto> getAllAnswers() {
        // Fetch all Answer entities from the database
        List<Answer> answers = answerRepository.findAll();

        // Check if no answers were found and throw a custom exception
        if (answers.isEmpty()) {
            throw new ResourceNotFoundException("No answers found in the database.");
        }

        // Convert each Answer entity to its corresponding DTO using the converter
        return answers.stream()
                .map(answerConverter::answerToAnswerDto) // Convert Answer -> AnswerDto
                .collect(Collectors.toList()); // Collect all DTOs into a list
    }

    /**
     * Retrieves an answer by its ID.
     *
     * @param id The ID of the answer to retrieve.
     * @return An AnswerDto object representing the requested answer.
     * @throws ResourceNotFoundException if no answer with the specified ID is found.
     */
    @Override
    public AnswerDto getAnswerById(Long id) {
        // Retrieve the Answer entity by ID from the repository
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer with ID " + id + " not found."));

        // Convert the retrieved entity to an AnswerDto and return it
        return answerConverter.answerToAnswerDto(answer);
    }

    /**
     * Updates an existing answer with the provided details.
     *
     * @param id        The ID of the answer to update.
     * @param answerDto The AnswerDto object containing the updated details.
     * @return An AnswerDto object representing the updated answer.
     * @throws ResourceNotFoundException if the answer with the specified ID does not exist.
     */
    @Override
    public AnswerDto updateAnswer(Long id, AnswerDto answerDto) {
        // Retrieve the existing answer entity by ID from the repository
        Answer existingAnswer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer with ID " + id + " not found."));

        // Update the fields of the existing answer entity using the provided DTO
//        existingAnswer.setSelectedAnswer(answerDto.getSelectedAnswer());
        existingAnswer.setIsActive(answerDto.getIsActive());

        // Save the updated entity back to the repository
        Answer updatedAnswer = answerRepository.save(existingAnswer);

        // Convert the updated entity to a DTO and return it
        return answerConverter.answerToAnswerDto(updatedAnswer);
    }

    /**
     * Soft deletes an answer by setting its isActive flag to false.
     *
     * @param id The ID of the answer to delete.
     * @throws ResourceNotFoundException if the answer with the specified ID does not exist.
     */
    @Override
    public void deleteAnswer(Long id) {
        // Retrieve the existing answer entity by ID from the repository
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer with ID " + id + " not found."));

        // Set the isActive flag to false for soft delete
        answer.setIsActive(false);

        // Save the updated entity back to the repository
        answerRepository.save(answer);
    }

}

