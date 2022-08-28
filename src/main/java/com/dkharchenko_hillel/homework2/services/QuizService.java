package com.dkharchenko_hillel.homework2.services;

import com.dkharchenko_hillel.homework2.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class QuizService {
    @Autowired
    private ResourceReader resourceReader;
    @Autowired
    private LanguageHandler languageHandler;
    @Autowired
    private User user;
    private int score;

    public void executeQuiz() {
        checkUser();
        List<String> answers = resourceReader.getPatternedElements();
        resourceReader.getRecords().forEach(
                record -> askQuestion(record, answers.get(resourceReader.getRecords().indexOf(record))));
        System.out.println(user.getFirstName() + " " + user.getLastName()
                + " " + languageHandler.getBundle().getString("score")
                + score + "/" + resourceReader.getRecords().size());
    }

    private void checkUser() {
        if (user.getFirstName() == null || user.getLastName() == null) {
            throw new IllegalArgumentException(languageHandler.getBundle().getString("registrationFail"));
        }
    }

    private void askQuestion(List<String> question, String rightAnswer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(languageHandler.getBundle().getString("chooseAnswer"));
        System.out.println(resourceReader.getFormattedOutput(question));
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(rightAnswer)) {
            score++;
            System.out.println(languageHandler.getBundle().getString("correctAnswer") + "\n");
        } else {
            System.out.println(languageHandler.getBundle().getString("wrongAnswer") + "\n");
        }
    }
}
