package com.dkharchenko_hillel.homework2;

import com.dkharchenko_hillel.homework2.services.LanguageHandler;
import com.dkharchenko_hillel.homework2.services.NameVerificationService;
import com.dkharchenko_hillel.homework2.services.QuizService;
import com.dkharchenko_hillel.homework2.services.ResourceReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuizApplicationConfiguration {
    private static final String RESOURCE_PATH = "questions.csv";

    @Bean(name = "nameVerificationService")
    public NameVerificationService nameVerificationService() {
        return new NameVerificationService();
    }

    @Bean(name = "languageHandler")
    public LanguageHandler languageHandler() {
        return new LanguageHandler();
    }

    @Bean(name = "user")
    public User user() {
        return new User();
    }

    @Bean(name = "quizService")
    public QuizService quizService() {
        return new QuizService();
    }

    @Bean(name = "resourceReader")
    public ResourceReader resourceReader() {
        return new ResourceReader(RESOURCE_PATH);
    }
}
