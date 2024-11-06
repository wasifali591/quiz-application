package in.theexplorers.quiz.configurations;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration class for Model Mapper
 * It creates a @Bean factory method to create ModelMapper instance
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setAmbiguityIgnored(true);
        return modelMapper;
    }
}
