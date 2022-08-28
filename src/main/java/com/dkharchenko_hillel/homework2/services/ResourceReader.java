package com.dkharchenko_hillel.homework2.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ResourceReader {
    @Autowired
    private LanguageHandler languageHandler;
    private final String resourcePath;
    private List<List<String>> records;
    private final static String CORRECT_ANSWER_PATTERN = " - правильный ответ";

    public ResourceReader(String resource) {
        this.resourcePath = resource;
    }

    @PostConstruct
    public void readResource() {
        records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new ClassPathResource(resourcePath).getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(languageHandler.getLocale().getLanguage())) {
                    String[] values = line.split(",");
                    records.add(new ArrayList<>(Arrays.asList(values)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPatternedElements() {
        List<String> correctAnswers = new ArrayList<>();
        records.forEach(record -> record.forEach(s -> {
            if (s.contains(CORRECT_ANSWER_PATTERN)) {
                correctAnswers.add(s.replace(CORRECT_ANSWER_PATTERN, ""));
                record.set(record.indexOf(s), s.replace(CORRECT_ANSWER_PATTERN, ""));
            }
        }));
        return correctAnswers;
    }

    public String getFormattedOutput(List<String> list) {
        list.remove(languageHandler.getLocale().getLanguage());
        StringBuilder builder = new StringBuilder();
        list.forEach(s -> builder.append(s).append(", "));
        builder.deleteCharAt(builder.lastIndexOf(", ")).deleteCharAt(builder.indexOf(", "));
        return builder.toString();
    }
}
