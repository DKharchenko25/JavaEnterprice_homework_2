package com.dkharchenko_hillel.homework2.services;

import com.dkharchenko_hillel.homework2.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class NameVerificationService {
    @Autowired
    private User user;
    @Autowired
    private LanguageHandler languageHandler;

    public void askNameAndLastname() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(languageHandler.getBundle().getString("firstName"));
        user.setFirstName(scanner.nextLine());
        checkIfCorrect(user.getFirstName());

        System.out.println(languageHandler.getBundle().getString("lastName"));
        user.setLastName(scanner.nextLine());
        checkIfCorrect(user.getLastName());
    }

    private void checkIfCorrect(String string) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        if (string.equals("") || matcher.lookingAt()) {
            throw new IllegalArgumentException(languageHandler.getBundle().getString("wrongInfo"));
        }
    }
}
