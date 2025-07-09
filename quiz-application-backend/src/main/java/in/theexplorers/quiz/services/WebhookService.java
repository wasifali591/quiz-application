package in.theexplorers.quiz.services;

public interface WebhookService {
    void triggerQuizActivationWebhook(Long quizId);
}
