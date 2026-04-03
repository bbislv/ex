package com.course.ex.service;

import com.course.ex.domain.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int availableQuestions = questionService.getAll().size();
        if (amount > availableQuestions) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Запрошено больше вопросов (" + amount +"), чем доступно (" + availableQuestions + ")");
        }

        Set<Question> selectedQuestions = new HashSet<>();
        List<Question> allQuestions = new ArrayList<>(questionService.getAll());

        while (selectedQuestions.size() < amount) {
            int randomIndex = random.nextInt(allQuestions.size());
            Question randomQuestion = allQuestions.get(randomIndex);
            selectedQuestions.add(randomQuestion);
        }

        return selectedQuestions;
    }
}
