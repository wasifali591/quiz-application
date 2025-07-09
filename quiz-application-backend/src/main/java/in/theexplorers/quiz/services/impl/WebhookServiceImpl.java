package in.theexplorers.quiz.services.impl;

import in.theexplorers.quiz.dtos.request.QuizActivationDto;
import in.theexplorers.quiz.services.WebhookService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookServiceImpl implements WebhookService {

    private final RestTemplate restTemplate;

    public WebhookServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void triggerQuizActivationWebhook(Long quizId) {
        String webhookUrl = "https://your-webhook-endpoint/api/webhooks/quiz-activation";
        QuizActivationDto payload = new QuizActivationDto(quizId);

        restTemplate.postForObject(webhookUrl, payload, Void.class);
    }
}
