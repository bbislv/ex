package com.course.ex.service;

import com.course.ex.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService questionService;
    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void addQuestionWithStringParams() {
        Question question = questionService.add("Что такое JVM?", "Java Virtual Machine");

        assertNotNull(question);
        assertEquals("Что такое JVM?", question.getQuestion());
        assertEquals("Java Virtual Machine", question.getAnswer());
        assertEquals(1, questionService.getAll().size());
    }

    @Test
    void addQuestionWithObject() {
        Question question = new Question("Что такое полиморфизм?","Возможность объекта вести себя по-разному");
        Question added = questionService.add(question);

        assertEquals(question, added);
        assertTrue(questionService.getAll().contains(question));
    }

    @Test
    void removeQuestion() {
        Question question = new Question("Вопрос", "Ответ");
        questionService.add(question);

        Question removed = questionService.remove(question);

        assertEquals(question, removed);
        assertFalse(questionService.getAll().contains(question));
    }

    @Test
    void getAllQuestions() {
        questionService.add("Вопрос 1", "Ответ 1");
        questionService.add("Вопрос 2", "Ответ 2");

        Collection<Question> all = questionService.getAll();

        assertEquals(2, all.size());
    }

    @Test
    void getRandomQuestion() {
        questionService.add("Вопрос 1", "Ответ 1");
        questionService.add("Вопрос 2", "Ответ 2");

        Question random = questionService.getRandomQuestion();

        assertNotNull(random);
        assertTrue(questionService.getAll().contains(random));
    }

    @Test
    void getRandomQuestionFromEmptyList() {
        Question random = questionService.getRandomQuestion();
        assertNull(random);
    }

    @Test
    void questionsAreUnique() {
        questionService.add("Одинаковый вопрос", "Одинаковый ответ");
        questionService.add("Одинаковый вопрос", "Одинаковый ответ");

        assertEquals(1, questionService.getAll().size());
    }
}
