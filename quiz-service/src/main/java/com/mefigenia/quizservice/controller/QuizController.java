package com.mefigenia.quizservice.controller;


import com.mefigenia.quizservice.model.QuestionWrapper;
import com.mefigenia.quizservice.model.QuizDto;
import com.mefigenia.quizservice.model.Response;
import com.mefigenia.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quiz) {
        return quizService.createQuiz(quiz.getCategory(), quiz.getNumQuestions(), quiz.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> getScore(@PathVariable int quizId, @RequestBody List<Response> response) {
        return quizService.getScore(quizId, response);
    }

}
