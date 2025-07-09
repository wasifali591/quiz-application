package in.theexplorers.quiz.services.impl;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.dtos.request.QuizSubmissionDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.dtos.response.QuizResultDto;
import in.theexplorers.quiz.entities.Question;
import in.theexplorers.quiz.entities.Quiz;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.QuestionRepository;
import in.theexplorers.quiz.repositories.QuizRepository;
import in.theexplorers.quiz.services.QuizService;
import in.theexplorers.quiz.utilities.converters.QuestionConverter;
import in.theexplorers.quiz.utilities.converters.QuizConverter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionConverter questionConverter;
    private final QuizConverter quizConverter;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository, QuestionConverter questionConverter, QuizConverter quizConverter) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.questionConverter = questionConverter;
        this.quizConverter = quizConverter;
    }

    @Override
    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(quizConverter::quizToQuizDto)
                .toList();
    }

    @Override
    public QuizDto getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
        return quizConverter.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        Quiz quiz = quizConverter.quizDtoToQuiz(quizDto);
        quiz = quizRepository.save(quiz);
        return quizConverter.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto updateQuiz(Long quizId, QuizDto quizDto) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setStartTime(quizDto.getStartTime());
        quiz.setEndTime(quizDto.getEndTime());
        quiz = quizRepository.save(quiz);

        return quizConverter.quizToQuizDto(quiz);
    }

    @Override
    public void deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
    }

    /**
     * Adds a question to a specific quiz.
     *
     * @param quizId      The ID of the quiz.
     * @param questionDto The details of the question to add.
     * @return The added question as a QuestionDto.
     * @throws ResourceNotFoundException If the quiz with the given ID does not exist.
     */
    @Transactional
    @Override
    public QuestionDto addQuestionToQuiz(Long quizId, QuestionDto questionDto) {
        // Fetch the quiz by ID
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + quizId));

        // Convert QuestionDto to Question entity
        Question question = questionConverter.questionDtoToQuestion(questionDto);

        // Associate the question with the quiz
        question.setQuiz(quiz);

        // Save the question in the database
        Question savedQuestion = questionRepository.save(question);

        // Convert and return the saved Question entity to DTO
        return questionConverter.questionToQuestionDto(savedQuestion);
    }

    @Override
    public QuizResultDto evaluateQuiz(Long quizId, QuizSubmissionDto submission) {
        // Evaluate logic
        return QuizResultDto.builder()
                .quizId(quizId)
                .userId(submission.getUserId())
                .correctAnswers(5) // Example: Computed based on submission
                .totalQuestions(10) // Example: Quiz question count
                .score(50) // Example: Computed score
                .build();
    }

    /**
     * @return
     */
    @Override
    public List<QuizDto> findInactiveQuizzesWithinTimeRange() {
        LocalDateTime now = LocalDateTime.now();

        // Fetch the list of inactive quizzes within the specified time range
        List<Quiz> quizList = quizRepository.findAllByStartTimeBeforeAndEndTimeAfterAndIsActiveFalse(now);

        // Return the DTO list
        return quizList.stream()
                .map(quizConverter::quizToQuizDto)
                .toList();
    }

}

