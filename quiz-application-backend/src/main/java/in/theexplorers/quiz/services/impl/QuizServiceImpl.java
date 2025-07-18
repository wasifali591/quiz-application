package in.theexplorers.quiz.services.impl;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.request.QuestionRequestDto;
import in.theexplorers.quiz.dtos.request.QuizRequestDto;
import in.theexplorers.quiz.dtos.request.QuizSubmissionDto;
import in.theexplorers.quiz.dtos.response.QuestionResponseDto;
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
        return quizRepository.findAll().stream().map(quizConverter::quizToQuizDto).toList();
    }

    @Override
    public QuizDto getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
        return quizConverter.quizToQuizDto(quiz);
    }

    /**
     * @param quizRequestDto
     * @return
     */
    @Override
    public QuizDto createQuiz(QuizRequestDto quizRequestDto) {
        Quiz quiz = quizConverter.quizRequestDtoToQuiz(quizRequestDto);
        // todo: need to remove after applying security
        quiz.setCreatedBy("SYSTEM");
        quiz = quizRepository.save(quiz);
        return quizConverter.quizToQuizDto(quiz);
    }


    /**
     * Updates an existing quiz with the given ID using the provided request data.
     *
     * @param quizId         ID of the quiz to be updated.
     * @param quizRequestDto DTO containing the updated quiz details.
     * @return Updated quiz details.
     */
    @Override
    public QuizDto updateQuiz(Long quizId, QuizRequestDto quizRequestDto) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        quiz = quizConverter.quizRequestDtoToQuiz(quizRequestDto, quiz);
//        quiz.setTitle(quizRequestDto.getTitle());
//        quiz.setDescription(quizRequestDto.getDescription());
//        quiz.setStartTime(quizRequestDto.getStartTime());
//        quiz.setEndTime(quizRequestDto.getEndTime());
        // todo: need to remove after applying security
        quiz.setUpdatedBy("SYSTEM");
        quiz = quizRepository.save(quiz);

        return quizConverter.quizToQuizDto(quiz);
    }

    @Transactional
    @Override
    public void deleteQuiz(Long quizId) {
        if (!quizRepository.existsById(quizId)) {
            throw new ResourceNotFoundException("Quiz not found with ID: " + quizId);
        }

        quizRepository.deleteById(quizId);
    }

    /**
     * Adds a question to a specific quiz.
     *
     * @param quizId             The ID of the quiz.
     * @param questionRequestDto The details of the question to add.
     * @return The added question as a QuestionDto.
     * @throws ResourceNotFoundException If the quiz with the given ID does not exist.
     */
    @Transactional
    @Override
    public QuestionResponseDto addQuestionToQuiz(Long quizId, QuestionRequestDto questionRequestDto) {
        // Fetch the quiz by ID
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + quizId));

        // Convert DTO to entity
        Question question = questionConverter.questionRequestDtoToQuestion(questionRequestDto);

        // Associate the question with the quiz
        question.setQuiz(quiz);

        // todo: remove after applying security
        question.setCreatedBy("SYSTEM");

        // 🔥 Associate each option with the question
        if (question.getOptions() != null) {
            question.getOptions().forEach(option -> {
                option.setQuestion(question);
                option.setCreatedBy("SYSTEM"); // TODO: Replace with authenticated username after enabling security
            });
        }

        // Save the question
        Question savedQuestion = questionRepository.save(question);

        // Return response
        return questionConverter.questionToQuestionResponseDto(savedQuestion);
    }

    @Override
    public QuizResultDto evaluateQuiz(Long quizId, QuizSubmissionDto submission) {
        // Evaluate logic
        return QuizResultDto.builder().quizId(quizId).userId(submission.getUserId()).correctAnswers(5) // Example: Computed based on submission
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
        return quizList.stream().map(quizConverter::quizToQuizDto).toList();
    }

}

