package com.example.demo.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizAnswer {
    private List<Integer> answer = new ArrayList<>();

    public List<Integer> getAnswer() {
        return this.answer;
    }

    public QuizAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public QuizAnswer() {
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
