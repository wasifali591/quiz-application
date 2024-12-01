package in.theexplorers.quiz.controllers;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.dtos.response.ApiResponseDto;
import in.theexplorers.quiz.services.AnswerService;
import in.theexplorers.quiz.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing {@link AnswerDto} entities.
 *
 * <p>This controller provides endpoints to add, update, delete, and retrieve answer records.
 * It uses {@link AnswerService} to handle the business logic and return standardized API
 * responses using {@link ApiResponseDto}.</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("quiz/api/answers")
public class AnswerController {

    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;

    /**
     * Constructor for {@link AnswerController}.
     *
     * @param answerService the service used to manage answer operations
     */
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * Retrieves all answers, optionally filtering by active status.
     *
     * <p>This endpoint fetches all answers in the system. An optional {@code isActive} parameter
     * can be provided to filter the answers based on their active status. If {@code isActive} is true,
     * only active answers will be returned; if false, only inactive answers will be returned.
     * If {@code isActive} is not specified, all active answers are retrieved by default.</p>
     *
     * @param isActive an optional Boolean flag for filtering answers by active status; if true, returns only active answers,
     *                 if false, returns only inactive answers, and defaults to true if not specified.
     * @return a {@link ResponseEntity} containing a list of {@link AnswerDto} objects if answers are found,
     * or a status message if no answers are available.
     */
    @Operation(
            summary = "Retrieve all answers",
            description = "Fetches all answers in the system, with an optional filter for active status."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answers retrieved successfully",
                    content = @Content(schema = @Schema(implementation = AnswerDto.class))),
            @ApiResponse(responseCode = "404", description = "No answers found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "isActive", required = false, defaultValue = "true") Boolean isActive) {

        logger.info(StringConstants.METHOD_START, "getAll");

        // Retrieve the list of answers based on active status
        List<AnswerDto> answerList = answerService.getAll(isActive);

        logger.info(StringConstants.METHOD_END, "getAll");

        // Return the response with a success message and the list of answers
        return new ApiResponseDto().generateResponse(HttpStatus.OK, answerList, "Answers retrieved successfully");
    }


    /**
     * Retrieves an answer by its unique ID, with an option to filter by active status.
     *
     * <p>This endpoint fetches a specific answer by its ID. It can also filter based on whether the answer
     * is active or inactive, depending on the provided {@code isActive} parameter.</p>
     *
     * @param id       the unique identifier of the answer
     * @param isActive an optional parameter (default is true) indicating whether to filter by active status;
     *                 if true, only active answers are considered, otherwise all answers are considered
     * @return a {@link ResponseEntity} containing the {@link AnswerDto} if the answer is found,
     * or a not-found status if the answer is not found
     */
    @Operation(summary = "Retrieve an answer by ID", description = "Fetches an answer by its unique ID, with an option to filter by active status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answer retrieved successfully",
                    content = @Content(schema = @Schema(implementation = AnswerDto.class))),
            @ApiResponse(responseCode = "404", description = "Answer not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "true") Boolean isActive) {

        logger.info(StringConstants.METHOD_START, "getById");

        // Call the service to retrieve the answer by ID with active status filtering
        AnswerDto answerDto = answerService.getById(id, isActive);

        logger.info(StringConstants.METHOD_END, "getById");

        // Return a standard API response containing the answer details
        return new ApiResponseDto().generateResponse(HttpStatus.OK, answerDto, "Answer retrieved successfully");
    }


    /**
     * Adds a new answer to the database.
     *
     * <p>Delegates the creation logic to {@link AnswerService#add(AnswerDto)}.</p>
     *
     * @param answerDto the {@link AnswerDto} containing new answer information
     * @return a {@link ResponseEntity} containing the created {@link AnswerDto} and a created status
     */
    @Operation(summary = "Add a new answer", description = "Adds a new answer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Answer created successfully",
                    content = @Content(schema = @Schema(implementation = AnswerDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody AnswerDto answerDto) {
        logger.info(StringConstants.METHOD_START, "add");
        AnswerDto savedAnswer = answerService.add(answerDto);
        logger.info(StringConstants.METHOD_END, "add");
        return new ApiResponseDto().generateResponse(HttpStatus.CREATED, savedAnswer, "Answer created successfully");
    }

    /**
     * Marks an answer as inactive instead of deleting it from the database.
     *
     * <p>Delegates the deletion logic to {@link AnswerService#delete(Long)}.</p>
     *
     * @param id the unique identifier of the answer to be marked as inactive
     * @return a {@link ResponseEntity} with a success message and OK status
     */
    @Operation(summary = "Delete an answer by ID", description = "Marks an answer as inactive instead of deleting.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answer marked as inactive"),
            @ApiResponse(responseCode = "404", description = "Answer not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        logger.info(StringConstants.METHOD_START, "delete");
        answerService.delete(id);
        logger.info(StringConstants.METHOD_END, "delete");
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Answer marked as inactive");
    }

    /**
     * Updates an existing answer with new information.
     *
     * <p>Delegates the update logic to {@link AnswerService#update(Long, AnswerDto)}.</p>
     *
     * @param id        the unique identifier of the answer to update
     * @param answerDto the {@link AnswerDto} containing updated answer information
     * @return a {@link ResponseEntity} containing the updated {@link AnswerDto} and a success message
     */
    @Operation(summary = "Update an existing answer", description = "Updates an answer by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answer updated successfully",
                    content = @Content(schema = @Schema(implementation = AnswerDto.class))),
            @ApiResponse(responseCode = "404", description = "Answer not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody AnswerDto answerDto) {
        logger.info(StringConstants.METHOD_START, "update");
        AnswerDto updatedAnswer = answerService.update(id, answerDto);
        logger.info(StringConstants.METHOD_END, "update");
        return new ApiResponseDto().generateResponse(HttpStatus.OK, updatedAnswer, "Answer updated successfully");
    }
}
