package com.dkharchenko_hillel.homework2;

import com.dkharchenko_hillel.homework2.services.NameVerificationService;
import com.dkharchenko_hillel.homework2.services.QuizService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(QuizApplicationConfiguration.class);
        NameVerificationService nameVerificationService = (NameVerificationService) context.getBean("nameVerificationService");
        QuizService quizService = (QuizService) context.getBean("quizService");

        nameVerificationService.askNameAndLastname();
        quizService.executeQuiz();



    }

}
