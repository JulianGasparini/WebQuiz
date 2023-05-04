package com.example.demo.Quiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QuizConfig {

    CommandLineRunner commandLineRunner(QuizRepository repository) {
        return args -> {
            Quiz test1 = new Quiz(
                    "Title",
                    "Body text",
                    List.of("Option1", "option2", "option3"),
                    List.of(1,2,3,4,5)

            );

            Quiz test2 = new Quiz(
                    "Title2",
                    "Body text2",
                    List.of("Option1", "option2", "option3"),
                    List.of(1,2,3,4,5)

            );

            repository.saveAll(List.of(test1, test2));
        };
    }
}
