package com.course.ex.service;

import com.course.ex.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ExaminerServiceImplTest {

    private ExaminerService examinerService;
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
        examinerService = new ExaminerServiceImpl(questionService);
        questionService.add("Вопрос 1", "Ответ 1");
        questionService.add("Вопрос 2", "Ответ 2");
        questionService.add("Вопрос 3", "Ответ 3");
        questionService.add("Вопрос 4", "Ответ 4");
        questionService.add("Вопрос 5", "Ответ 5");
    }

    @Test
    void getQuestionsReturnsCorrectAmount() {
        Collection<Question> questions = examinerService.getQuestions(3);

        assertEquals(3, questions.size());
    }

    @Test
    void getQuestionsReturnsUniqueQuestions() {
        Collection<Question> questions = examinerService.getQuestions(5);

        assertEquals(5, questions.size());
    }

    @Test
    void getQuestionsThrowsExceptionWhenAmountTooLarge() {
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> examinerService.getQuestions(10)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void getQuestionsReturnsAllAvailableWhenAmountEqualsSize() {
        Collection<Question> questions = examinerService.getQuestions(5);

        assertEquals(5, questions.size());
    }

    @Test
    void getQuestionsReturnsEmptyCollectionWhenAmountIsZero() {
        Collection<Question> questions = examinerService.getQuestions(0);

        assertTrue(questions.isEmpty());
    }
}
