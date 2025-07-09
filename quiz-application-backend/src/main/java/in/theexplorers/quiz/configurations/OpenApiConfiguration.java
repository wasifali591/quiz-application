package in.theexplorers.quiz.configurations;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration class for OpenAPI documentation.
 * It customizes metadata for the OpenAPI 3 documentation.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class OpenApiConfiguration {

    /**
     * Defines the OpenAPI 3 documentation configuration with custom metadata.
     *
     * @return {@link OpenAPI} instance with API information.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Quiz API")
                        .description("Backend Spring Boot Project of Quiz")
                        .version("1.0.0")
                        .termsOfService("https://example.com/terms")
                        .contact(new Contact()
                                .name("TheExplorers")
                                .url("https://theexplorers.com")
                                .email("ops@theexplorers.com"))
                        .license(new License()
                                .name("API License")
                                .url("https://xyz.com")));
    }
}
