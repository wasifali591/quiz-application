package in.theexplorers.quiz.controllers;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.dtos.response.ApiResponseDto;
import in.theexplorers.quiz.services.AnswerService;
import in.theexplorers.quiz.utilities.DateTimeUtility;
import in.theexplorers.quiz.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/answers")
@Tag(name = "Answer Controller", description = "APIs for managing answers")
public class AnswerController {

    private final AnswerService answerService;
    private final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * Fetch all answers.
     *
     * @return List of all AnswerDto objects.
     */
    @Operation(summary = "Get all answers", description = "Retrieve all answers from the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all answers")
    @GetMapping
    public ResponseEntity<ApiResponseDto> getAllAnswers() {
        logger.info(StringConstants.METHOD_START, "getAllAnswers");
        List<AnswerDto> answers = answerService.getAllAnswers();
        logger.info(StringConstants.METHOD_END, "getAllAnswers");
        return ApiResponseDto.generateResponse(
                HttpStatus.OK, answers, "All answers retrieved successfully", LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
    }

    /**
     * Fetch an answer by its ID.
     *
     * @param id Answer ID.
     * @return AnswerDto object corresponding to the given ID.
     */
    @Operation(summary = "Get an answer by ID", description = "Retrieve an answer using its ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the answer")
    @ApiResponse(responseCode = "404", description = "Answer not found with the provided ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getAnswerById(@PathVariable Long id) {
        logger.info(StringConstants.METHOD_START, "getAnswerById");
        AnswerDto answer = answerService.getAnswerById(id);
        logger.info(StringConstants.METHOD_END, "getAnswerById");
        return ApiResponseDto.generateResponse(
                HttpStatus.OK, answer, "Answer retrieved successfully", LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
    }

    /**
     * Create a new answer.
     *
     * @param answerDto Answer data to be created.
     * @return The created AnswerDto object.
     */
    @Operation(summary = "Create a new answer", description = "Add a new answer to the system.")
    @ApiResponse(responseCode = "201", description = "Successfully created the answer")
    @PostMapping
    public ResponseEntity<ApiResponseDto> createAnswer(@RequestBody AnswerDto answerDto) {
        logger.info(StringConstants.METHOD_START, "createAnswer");
        AnswerDto createdAnswer = answerService.submitAnswer(answerDto);
        logger.info(StringConstants.METHOD_END, "createAnswer");
        return ApiResponseDto.generateResponse(
                HttpStatus.CREATED, createdAnswer, "Answer created successfully", LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
    }

    /**
     * Update an existing answer by ID.
     *
     * @param id        Answer ID.
     * @param answerDto Updated answer data.
     * @return Updated AnswerDto object.
     */
    @Operation(summary = "Update an answer", description = "Update an existing answer using its ID.")
    @ApiResponse(responseCode = "200", description = "Successfully updated the answer")
    @ApiResponse(responseCode = "404", description = "Answer not found with the provided ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> updateAnswer(@PathVariable Long id, @RequestBody AnswerDto answerDto) {
        logger.info(StringConstants.METHOD_START, "updateAnswer");
        AnswerDto updatedAnswer = answerService.updateAnswer(id, answerDto);
        logger.info(StringConstants.METHOD_END, "updateAnswer");
        return ApiResponseDto.generateResponse(
                HttpStatus.OK, updatedAnswer, "Answer updated successfully", LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
    }

    /**
     * Delete an answer by ID.
     *
     * @param id Answer ID.
     * @return Confirmation message.
     */
    @Operation(summary = "Delete an answer", description = "Remove an answer from the system using its ID.")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the answer")
    @ApiResponse(responseCode = "404", description = "Answer not found with the provided ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteAnswer(@PathVariable Long id) {
        logger.info(StringConstants.METHOD_START, "deleteAnswer");
        answerService.deleteAnswer(id);
        logger.info(StringConstants.METHOD_END, "deleteAnswer");
        return ApiResponseDto.generateResponse(
                HttpStatus.OK, null, "Answer deleted successfully", LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
    }
}

