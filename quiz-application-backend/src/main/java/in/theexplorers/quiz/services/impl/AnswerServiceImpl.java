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
import org.springframework.stereotype.Service;

import java.util.List;
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
}

