package in.theexplorers.quiz.configurations;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for defining RestTemplate as a Spring Bean.
 * This allows for centralized HTTP client setup used in service classes.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates and returns a RestTemplate bean for performing HTTP requests.
     *
     * @return RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
