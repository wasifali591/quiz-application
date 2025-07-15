package in.theexplorers.quiz.controllers;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.dtos.request.OptionRequestDto;
import in.theexplorers.quiz.dtos.request.QuestionRequestDto;
import in.theexplorers.quiz.dtos.response.ApiResponseDto;
import in.theexplorers.quiz.dtos.response.OptionResponseDto;
import in.theexplorers.quiz.dtos.response.QuestionResponseDto;
import in.theexplorers.quiz.services.OptionService;
import in.theexplorers.quiz.services.QuestionService;
import in.theexplorers.quiz.utilities.DateTimeUtility;
import in.theexplorers.quiz.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Controller for managing questions, options, and answers in the quiz application.
 *
 * <p>Provides CRUD operations for questions and their associated options, as well as endpoints for submitting and retrieving answers.</p>
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/questions")
@Tag(name = "Question Controller", description = "APIs for managing questions")
public class QuestionController {

    private final QuestionService questionService;
    private final OptionService optionService;

    public QuestionController(QuestionService questionService, OptionService optionService) {
        this.questionService = questionService;
        this.optionService = optionService;
    }

    // -------------------- Question Endpoints --------------------

    /**
     * Retrieve all questions from the system.
     *
     * @return a ResponseEntity containing a list of {@link QuestionDto} and a success message.
     */
    @Operation(summary = "Get all questions", description = "Retrieve all questions from the system.")
    @ApiResponse(responseCode = "200", description = "Questions retrieved successfully", content = @Content(schema = @Schema(implementation = QuestionDto.class)))
    @GetMapping
    public ResponseEntity<ApiResponseDto> getAllQuestions() {
        log.info(StringConstants.METHOD_START, "getAllQuestions");
        List<QuestionResponseDto> questions = questionService.getAllQuestions();
        log.info(StringConstants.METHOD_END, "getAllQuestions");
        return ApiResponseDto.generateResponse(HttpStatus.OK, questions, "Questions retrieved successfully", LocalDateTime.now());
    }

    /**
     * Retrieve a specific question by its ID.
     *
     * @param id the ID of the question to retrieve.
     * @return a ResponseEntity containing the {@link QuestionDto} and a success message.
     */
    @Operation(summary = "Get a question by ID", description = "Retrieve a specific question by its ID.")
    @ApiResponse(responseCode = "200", description = "Question retrieved successfully", content = @Content(schema = @Schema(implementation = QuestionDto.class)))
    @ApiResponse(responseCode = "404", description = "Question not found", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getQuestionById(@PathVariable Long id) {
        log.info(StringConstants.METHOD_START, "getQuestionById");
        QuestionResponseDto question = questionService.getQuestionById(id);
        log.info(StringConstants.METHOD_END, "getQuestionById");
        return ApiResponseDto.generateResponse(HttpStatus.OK, question, "Question retrieved successfully", LocalDateTime.now());
    }

//    /**
//     * Add a new question to the system.
//     *
//     * @param questionDto the details of the question to create.
//     * @return a ResponseEntity containing the created {@link QuestionDto} and a success message.
//     */
//    @Operation(summary = "Create a new question", description = "Add a new question to the system.")
//    @ApiResponse(responseCode = "201", description = "Question created successfully", content = @Content(schema = @Schema(implementation = QuestionDto.class)))
//    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
//    @PostMapping
//    public ResponseEntity<ApiResponseDto> createQuestion(@RequestBody QuestionDto questionDto) {
//        log.info(StringConstants.METHOD_START, "createQuestion");
//        QuestionDto createdQuestion = questionService.createQuestion(questionDto);
//        log.info(StringConstants.METHOD_END, "createQuestion");
//        return ApiResponseDto.generateResponse(HttpStatus.CREATED, createdQuestion, "Question created successfully", LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
//    }

    /**
     * Update an existing question by its ID.
     *
     * @param id                 the ID of the question to update.
     * @param questionRequestDto the updated question details.
     * @return a ResponseEntity containing the updated {@link QuestionDto} and a success message.
     */
    @Operation(summary = "Update an existing question", description = "Update a question by its ID.")
    @ApiResponse(responseCode = "200", description = "Question updated successfully", content = @Content(schema = @Schema(implementation = QuestionDto.class)))
    @ApiResponse(responseCode = "404", description = "Question not found", content = @Content)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionRequestDto questionRequestDto) {
        log.info(StringConstants.METHOD_START, "updateQuestion");
        QuestionResponseDto updatedQuestion = questionService.updateQuestion(id, questionRequestDto);
        log.info(StringConstants.METHOD_END, "updateQuestion");
        return ApiResponseDto.generateResponse(HttpStatus.OK, updatedQuestion, "Question updated successfully", LocalDateTime.now());
    }

    /**
     * Delete a question by its ID.
     *
     * @param id the ID of the question to delete.
     * @return a ResponseEntity containing a success message.
     */
    @Operation(summary = "Delete a question", description = "Delete a question by its ID.")
    @ApiResponse(responseCode = "200", description = "Question deleted successfully")
    @ApiResponse(responseCode = "404", description = "Question not found", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteQuestion(@PathVariable Long id) {
        log.info(StringConstants.METHOD_START, "deleteQuestion");
        questionService.deleteQuestion(id);
        log.info(StringConstants.METHOD_END, "deleteQuestion");
        return ApiResponseDto.generateResponse(HttpStatus.OK, null, "Question deleted successfully", LocalDateTime.now());
    }

    // -------------------- Option Endpoints --------------------

    /**
     * Add an option for a specific question.
     *
     * @param questionId the ID of the question to add an option to.
     * @param optionRequestDto  the option details.
     * @return a ResponseEntity containing the created {@link OptionDto} and a success message.
     */
    @Operation(summary = "Add an option to a question", description = "Add an option for a specific question.")
    @ApiResponse(responseCode = "201", description = "Option added successfully", content = @Content(schema = @Schema(implementation = OptionDto.class)))
    @ApiResponse(responseCode = "404", description = "Question not found", content = @Content)
    @PostMapping("/{questionId}/options")
    public ResponseEntity<ApiResponseDto> addOptionToQuestion(@PathVariable Long questionId, @RequestBody OptionRequestDto optionRequestDto) {
        log.info(StringConstants.METHOD_START, "addOptionToQuestion");
        OptionResponseDto createdOption = optionService.addOption(questionId, optionRequestDto);
        log.info(StringConstants.METHOD_END, "addOptionToQuestion");
        return ApiResponseDto.generateResponse(HttpStatus.CREATED, createdOption, "Option added successfully", LocalDateTime.now());
    }

    /**
     * Retrieve all options for a specific question.
     *
     * @param questionId the ID of the question to get options for.
     * @return a ResponseEntity containing a list of {@link OptionDto} and a success message.
     */
    @Operation(summary = "Get all options for a question", description = "Retrieve all options for a specific question.")
    @ApiResponse(responseCode = "200", description = "Options retrieved successfully", content = @Content(schema = @Schema(implementation = OptionDto.class)))
    @ApiResponse(responseCode = "404", description = "Question not found", content = @Content)
    @GetMapping("/{questionId}/options")
    public ResponseEntity<ApiResponseDto> getOptionsForQuestion(@PathVariable Long questionId) {
        log.info(StringConstants.METHOD_START, "getOptionsForQuestion");
        List<OptionResponseDto> options = optionService.getOptionsByQuestionId(questionId);
        log.info(StringConstants.METHOD_END, "getOptionsForQuestion");
        return ApiResponseDto.generateResponse(HttpStatus.OK, options, "Options retrieved successfully", LocalDateTime.now());
    }

    // -------------------- Answer Endpoints --------------------

    /**
     * Retrieve all answers submitted for a specific question.
     *
     * @param questionId the ID of the question to get answers for.
     * @return a ResponseEntity containing a list of {@link AnswerDto} and a success message.
     */
    @Operation(summary = "Get all answers for a question", description = "Retrieve all answers submitted for a specific question.")
    @ApiResponse(responseCode = "200", description = "Answers retrieved successfully", content = @Content(schema = @Schema(implementation = AnswerDto.class)))
    @ApiResponse(responseCode = "404", description = "Question not found", content = @Content)
    @GetMapping("/{questionId}/answers")
    public ResponseEntity<ApiResponseDto> getAnswersForQuestion(@PathVariable Long questionId) {
        log.info(StringConstants.METHOD_START, "getAnswersForQuestion");
        List<AnswerDto> answers = questionService.getAnswersForQuestion(questionId);
        log.info(StringConstants.METHOD_END, "getAnswersForQuestion");
        return ApiResponseDto.generateResponse(HttpStatus.OK, answers, "Answers retrieved successfully", LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
    }
}
