package in.theexplorers.quiz.controllers;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.request.QuizActivationDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.services.QuizService;
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
public class WebhookController {

    private final QuizService quizService;

    public WebhookController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quiz-activation")
    public void handleQuizActivation(@RequestBody QuizActivationDto payload) {
        // Fetch the quiz by ID; let the service handle exceptions
        QuizDto quizDto = quizService.getQuizById(payload.getQuizId());

        // Set the quiz as active
        quizDto.setActive(true);

        // Update the quiz
        quizService.updateQuiz(quizDto.getId(), quizDto);
    }

}
