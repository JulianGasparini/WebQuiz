package com.example.demo.Quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/quizzes/")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuiz(@PathVariable long id) {
        return ResponseEntity.ok(quizService.getQuizDTOById(id));
    }


    @PostMapping()
    public ResponseEntity<QuizDTO> addQuiz(@RequestBody @Validated Quiz quiz) {

        Quiz newQuiz = new Quiz(quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer());

        long id = quizService.add(newQuiz);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.
                status(HttpStatus.OK).
                location(location).
                body(quizService.getQuizDTOById(id));
    }

    @PostMapping("/{id}/solve")
    public Feedback responseAnswer(@PathVariable long id, @RequestBody @Validated QuizAnswer answer) {
        List<Integer> quizAnswers = quizService.getQuizAnswerByID(id);
        List<Integer> answersFromPost = answer.getAnswer();
        if (quizAnswers.size() < answersFromPost.size()) {
            return new Feedback(false, "Wrong answer! Please, try again.");
        }

        for (Integer option : quizAnswers
        ) {
            if (!answersFromPost.contains(option)) {

                return new Feedback(false, "Wrong answer! Please, try again.");
            }
        }
        return new Feedback(true, "Congratulations, you're right!");
    }

    private QuizDTO convertQuizToQuizDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }
}
