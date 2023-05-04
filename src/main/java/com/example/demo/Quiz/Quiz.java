package com.example.demo.Quiz;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Quiz {

    @Id
    @SequenceGenerator( name = "quiz_sequence",
                        sequenceName = "quiz_sequence",
                        allocationSize = 1
    )
    @GeneratedValue(    strategy = GenerationType.SEQUENCE,
                        generator = "quiz_sequence"
    )
    private long id;
    private String title;
    private String text;


    @ElementCollection
    @CollectionTable
    private List<String> options;

    @ElementCollection
    @CollectionTable
    private List<Integer> answer;

    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Quiz() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
