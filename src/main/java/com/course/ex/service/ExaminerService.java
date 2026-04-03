package com.course.ex.service;

import com.course.ex.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
