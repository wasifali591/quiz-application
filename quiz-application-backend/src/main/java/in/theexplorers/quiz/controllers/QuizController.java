package in.theexplorers.quiz.controllers;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.request.QuestionRequestDto;
import in.theexplorers.quiz.dtos.request.QuizRequestDto;
import in.theexplorers.quiz.dtos.response.ApiResponseDto;
import in.theexplorers.quiz.dtos.response.QuestionResponseDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.services.QuestionService;
import in.theexplorers.quiz.services.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Controller for managing quizzes in the quiz application.
 *
 * <p>Provides CRUD operations for quizzes and their associated question, answer as well as user interactions.</p>
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/quizzes")
@Tag(name = "Quiz Controller", description = "APIs for managing quizzes")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;

    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    // CRUD Operations for Quizzes

    /**
     * Get all quizzes.
     *
     * @return List of all quizzes.
     */
    @Operation(summary = "Get all quizzes", description = "Retrieves all available quizzes.")
    @ApiResponse(responseCode = "200", description = "Quizzes retrieved successfully")
    @GetMapping
    public ResponseEntity<ApiResponseDto> getAllQuizzes() {
        List<QuizDto> quizzes = quizService.getAllQuizzes();
        return ApiResponseDto.generateResponse(HttpStatus.OK, quizzes, "Quizzes retrieved successfully", LocalDateTime.now());
    }

    /**
     * Get quiz details by ID.
     *
     * @param quizId ID of the quiz to retrieve.
     * @return Quiz details.
     */
    @Operation(summary = "Get quiz details", description = "Fetches details of a specific quiz by its ID.")
    @ApiResponse(responseCode = "200", description = "Quiz details retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Quiz not found")
    @GetMapping("/{quizId}")
    public ResponseEntity<ApiResponseDto> getQuizById(@PathVariable Long quizId) {
        QuizDto quiz = quizService.getQuizById(quizId);
        return ApiResponseDto.generateResponse(HttpStatus.OK, quiz, "Quiz details retrieved successfully", LocalDateTime.now());
    }

    /**
     * Create a new quiz.
     *
     * @param quizRequestDto Details of the quiz to create.
     * @return Created quiz details.
     */
    @Operation(summary = "Create a new quiz", description = "Creates a new quiz with the given details.")
    @ApiResponse(responseCode = "201", description = "Quiz created successfully")
    @PostMapping
    public ResponseEntity<ApiResponseDto> createQuiz(@Valid @RequestBody QuizRequestDto quizRequestDto) {
        QuizDto createdQuiz = quizService.createQuiz(quizRequestDto);
        return ApiResponseDto.generateResponse(HttpStatus.CREATED, createdQuiz, "Quiz created successfully", LocalDateTime.now());
    }

    /**
     * Update a quiz by ID.
     *
     * @param quizId         ID of the quiz to update.
     * @param quizRequestDto Updated quiz details.
     * @return Updated quiz details.
     */
    @Operation(summary = "Update a quiz", description = "Updates the details of an existing quiz by its ID.")
    @ApiResponse(responseCode = "200", description = "Quiz updated successfully")
    @ApiResponse(responseCode = "404", description = "Quiz not found")
    @PutMapping("/{quizId}")
    public ResponseEntity<ApiResponseDto> updateQuiz(@PathVariable Long quizId, @Valid @RequestBody QuizRequestDto quizRequestDto) {
        QuizDto updatedQuiz = quizService.updateQuiz(quizId, quizRequestDto);
        return ApiResponseDto.generateResponse(HttpStatus.OK, updatedQuiz, "Quiz updated successfully", LocalDateTime.now());
    }

    /**
     * Delete a quiz by ID.
     *
     * @param quizId ID of the quiz to delete.
     * @return Deletion confirmation.
     */
    @Operation(summary = "Delete a quiz", description = "Deletes a quiz by its ID.")
    @ApiResponse(responseCode = "200", description = "Quiz deleted successfully")
    @ApiResponse(responseCode = "404", description = "Quiz not found")
    @DeleteMapping("/{quizId}")
    public ResponseEntity<ApiResponseDto> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ApiResponseDto.generateResponse(HttpStatus.OK, null, "Quiz deleted successfully", LocalDateTime.now());
    }

    // -------------------- Question Endpoints --------------------

    /**
     * Get all questions in a quiz.
     *
     * @param quizId ID of the quiz.
     * @return List of questions in the quiz.
     */
    @Operation(summary = "Get all questions in a quiz", description = "Retrieves all questions for a given quiz.")
    @ApiResponse(responseCode = "200", description = "Questions retrieved successfully")
    @GetMapping("/{quizId}/questions")
    public ResponseEntity<ApiResponseDto> getQuestionsInQuiz(@PathVariable Long quizId) {
        List<QuestionResponseDto> questions = questionService.getQuestionsByQuizId(quizId);
        return ApiResponseDto.generateResponse(HttpStatus.OK, questions, "Questions retrieved successfully", LocalDateTime.now());
    }

    /**
     * Add questions to a quiz.
     *
     * @param quizId             ID of the quiz.
     * @param questionRequestDto List of questions to add.
     * @return List of added questions.
     */
    @Operation(summary = "Add questions to a quiz", description = "Adds a list of questions to a quiz.")
    @ApiResponse(responseCode = "201", description = "Questions added successfully")
    @PostMapping("/{quizId}/questions")
    public ResponseEntity<ApiResponseDto> addQuestionsToQuiz(@PathVariable Long quizId, @RequestBody QuestionRequestDto questionRequestDto) {
        QuestionResponseDto addedQuestions = quizService.addQuestionToQuiz(quizId, questionRequestDto);
        return ApiResponseDto.generateResponse(HttpStatus.CREATED, addedQuestions, "Questions added successfully", LocalDateTime.now());
    }
}

