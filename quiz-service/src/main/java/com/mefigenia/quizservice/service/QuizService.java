package com.mefigenia.quizservice.service;


import com.mefigenia.quizservice.dao.QuizDao;
import com.mefigenia.quizservice.feign.QuizInterface;
import com.mefigenia.quizservice.model.Question;
import com.mefigenia.quizservice.model.QuestionWrapper;
import com.mefigenia.quizservice.model.Quiz;
import com.mefigenia.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {
        List<Integer> questionsId = quizInterface.getQuestionsForQuiz(category, numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionListId(questionsId);

        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questions = quiz.get().getQuestionListId();
        ResponseEntity<List<QuestionWrapper>> questionForUser =  quizInterface.getQuestionsFromId(questions);

        return questionForUser;

    }

    public ResponseEntity<Integer> getScore(int quizId, List<Response> response) {
        ResponseEntity<Integer> rightAnswers = quizInterface.getScore(response);

         return rightAnswers;
    }
}
