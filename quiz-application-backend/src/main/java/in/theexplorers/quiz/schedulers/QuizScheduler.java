package in.theexplorers.quiz.schedulers;
/*
 * Copyright (c) 2024 TheExplorers.
 */


import in.theexplorers.quiz.services.QuizService;
import in.theexplorers.quiz.services.WebhookService;
import in.theexplorers.quiz.utilities.converters.QuizConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * REST Controller for managing quizzes in the quiz application.
 *
 * <p>Provides CRUD operations for quizzes and their associated question, answer as well as user interactions.</p>
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class QuizScheduler {

    private final WebhookService webhookService;
    private final QuizService quizService;
    private final QuizConverter quizConverter;

    public QuizScheduler(WebhookService webhookService, QuizService quizService, QuizConverter quizConverter) {
        this.webhookService = webhookService;
        this.quizService = quizService;
        this.quizConverter = quizConverter;
    }

    @Scheduled(fixedRate = 60000) // Runs every 1 minute
    public void activateQuizzes() {
        quizService.findInactiveQuizzesWithinTimeRange().forEach(quiz -> {
            // Update quiz status
            quiz.setActive(true);
            quizService.updateQuiz(quiz.getId(), quizConverter.quizDtoToQuizRequestDto(quiz));

            // Trigger webhook
            webhookService.triggerQuizActivationWebhook(quiz.getId());
        });
    }
}
