package in.theexplorers.quiz.controllers;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.request.QuizActivationDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.services.QuizService;
import in.theexplorers.quiz.utilities.converters.QuizConverter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/webhooks")
@Tag(name = "Webhook Controller", description = "APIs for managing webhooks")
public class WebhookController {

    private final QuizService quizService;
    private final QuizConverter quizConverter;

    public WebhookController(QuizService quizService, QuizConverter quizConverter) {
        this.quizService = quizService;
        this.quizConverter = quizConverter;
    }

    @PostMapping("/quiz-activation")
    public void handleQuizActivation(@RequestBody QuizActivationDto payload) {
        // Fetch the quiz by ID; let the service handle exceptions
        QuizDto quizDto = quizService.getQuizById(payload.getQuizId());

        // Set the quiz as active
        quizDto.setActive(true);

        // Update the quiz
        quizService.updateQuiz(quizDto.getId(), quizConverter.quizDtoToQuizRequestDto(quizDto));
    }

}
