package in.theexplorers.quiz.controllers;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.dtos.response.ApiResponseDto;
import in.theexplorers.quiz.services.OptionService;
import in.theexplorers.quiz.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * REST Controller for managing questions in the quiz application.
 *
 * <p>Provides CRUD operations for options.</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/options")
@Tag(name = "Options API", description = "Endpoints for managing options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    /**
     * Retrieves an option by its ID.
     *
     * @param id the ID of the option to retrieve.
     * @return the OptionDto containing option details.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an option by ID", description = "Fetches an option using its unique ID.")
    @ApiResponse(responseCode = "200", description = "Option retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Option not found")
    public ResponseEntity<ApiResponseDto> getOptionById(@PathVariable Long id) {
        log.info(StringConstants.METHOD_START, "getOptionById");
        OptionDto option = optionService.getOptionById(id);
        log.info(StringConstants.METHOD_END, "getOptionById");
        return ApiResponseDto.generateResponse(
                HttpStatus.OK, option, "Option retrieved successfully", LocalDateTime.now());
    }

    /**
     * Updates an existing option by its ID.
     *
     * @param id        the ID of the option to update.
     * @param optionDto the updated option details.
     * @return the updated OptionDto.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an option by ID", description = "Updates the details of an existing option.")
    @ApiResponse(responseCode = "200", description = "Option updated successfully")
    @ApiResponse(responseCode = "404", description = "Option not found")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<ApiResponseDto> updateOptionById(@PathVariable Long id, @RequestBody OptionDto optionDto) {
        log.info(StringConstants.METHOD_START, "updateOptionById");
        OptionDto updatedOption = optionService.updateOptionById(id, optionDto);
        log.info(StringConstants.METHOD_END, "updateOptionById");
        return ApiResponseDto.generateResponse(
                HttpStatus.OK, updatedOption, "Option updated successfully", LocalDateTime.now());
    }

    /**
     * Deletes an option by its ID.
     *
     * @param id the ID of the option to delete.
     * @return a confirmation message.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an option by ID", description = "Deletes an option based on its unique ID.")
    @ApiResponse(responseCode = "200", description = "Option deleted successfully")
    @ApiResponse(responseCode = "404", description = "Option not found")
    public ResponseEntity<ApiResponseDto> deleteOptionById(@PathVariable Long id) {
        log.info(StringConstants.METHOD_START, "deleteOptionById");
        optionService.deleteOptionById(id);
        log.info(StringConstants.METHOD_END, "deleteOptionById");
        return ApiResponseDto.generateResponse(
                HttpStatus.OK, null, "Option deleted successfully", LocalDateTime.now());
    }
}

