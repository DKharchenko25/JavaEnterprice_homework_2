package com.dkharchenko_hillel.homework2.services;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

@Getter
@Setter
public class LanguageHandler {
    private ResourceBundle bundle;
    private Locale locale;

    @PostConstruct
    public void setApplicationLanguage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose language (en, fr, de):");
        String answer = scanner.nextLine();
        locale = new Locale(answer);
        bundle = ResourceBundle.getBundle("language", locale);
    }
}
