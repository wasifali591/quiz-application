package in.theexplorers.quiz.schedulers;
/*
 * Copyright (c) 2024 TheExplorers.
 */


import in.theexplorers.quiz.services.QuizService;
import in.theexplorers.quiz.services.WebhookService;
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

    public QuizScheduler(WebhookService webhookService,
                         QuizService quizService) {
        this.webhookService = webhookService;
        this.quizService = quizService;
    }

    @Scheduled(fixedRate = 60000) // Runs every 1 minute
    public void activateQuizzes() {
        quizService.findInactiveQuizzesWithinTimeRange()
                .forEach(quiz -> {
                    // Update quiz status
                    quiz.setActive(true);
                    quizService.updateQuiz(quiz.getId(), quiz);

                    // Trigger webhook
                    webhookService.triggerQuizActivationWebhook(quiz.getId());
                });
    }
}
