package com.example.demo.Quiz;

import com.example.demo.Exception.ApiRequestException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<QuizDTO> getQuizzes(){
        return quizListToDTOList(quizRepository.findAll());
    }

    public QuizDTO getQuizDTOById(long id) {
        return quizToQuizDTO(getQuizById(id));
    }

    public List<Integer> getQuizAnswerByID(long id) {
        Quiz q = getQuizById(id);
        List<Integer> answers = q.getAnswer();

        return answers;
    }
    private Quiz getQuizById (long id) {
        try {
            Quiz q = quizRepository.getReferenceById(id);
            return q;
        } catch (EntityNotFoundException e) {
            throw new ApiRequestException("Quiz not found");
        }
    }

    public long getAmountOfQuizzes(){
        return quizRepository.count();
    }

    public long add(Quiz quiz) {
       quizRepository.save(quiz);
       return quiz.getId();
    }

    private QuizDTO quizToQuizDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }

    private List<QuizDTO> quizListToDTOList (List<Quiz> quizList) {
        List<QuizDTO> filteredQuizzes = new ArrayList<>();

        for (Quiz q: quizRepository.findAll()
        ) {
            filteredQuizzes.add(quizToQuizDTO(q));
        }

        return filteredQuizzes;
    }

}
