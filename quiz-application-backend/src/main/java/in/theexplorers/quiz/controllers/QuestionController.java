package in.theexplorers.quiz.controllers;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/questions")
@Tag(name = "Question Management", description = "Operations for managing questions and their options")
public class QuestionController {

    private final QuestionService questionService;
    private final OptionService optionService;

    public QuestionController(QuestionService questionService, OptionService optionService) {
        this.questionService = questionService;
        this.optionService = optionService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllQuestions() {
        List<QuestionDto> questions = questionService.getAllQuestions();
        return new ApiResponseDto().generateResponse(HttpStatus.OK, questions, "Questions retrieved successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getQuestionById(@PathVariable Long id) {
        QuestionDto question = questionService.getQuestionById(id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, question, "Question retrieved successfully");
    }

    @PostMapping
    public ResponseEntity<Object> createQuestion(@RequestBody @Valid QuestionDto questionDto) {
        QuestionDto createdQuestion = questionService.createQuestion(questionDto);
        return new ApiResponseDto().generateResponse(HttpStatus.CREATED, createdQuestion, "Question created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateQuestion(@PathVariable Long id, @RequestBody @Valid QuestionDto questionDto) {
        QuestionDto updatedQuestion = questionService.updateQuestion(id, questionDto);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, updatedQuestion, "Question updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Question deleted successfully");
    }

    @PostMapping("/{questionId}/options")
    public ResponseEntity<Object> createOption(@PathVariable Long questionId, @RequestBody @Valid OptionDto optionDto) {
        OptionDto createdOption = optionService.createOption(questionId, optionDto);
        return new ApiResponseDto().generateResponse(HttpStatus.CREATED, createdOption, "Option created successfully");
    }

    @GetMapping("/{questionId}/options")
    public ResponseEntity<Object> getOptionsByQuestion(@PathVariable Long questionId) {
        List<OptionDto> options = optionService.getOptionsByQuestion(questionId);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, options, "Options retrieved successfully");
    }
}
