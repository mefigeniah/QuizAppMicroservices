package com.mefigenia.quizservice.model;

import lombok.Data;


@Data
public class QuizDto {
    private String category;
    private int numQuestions;
    private String title;
}
