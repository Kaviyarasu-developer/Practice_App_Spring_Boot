package com.practice_app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.QuestionCreateDto;
import com.practice_app.dtos.QuestionDto;
import com.practice_app.models.QuestionEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.QuestionRepository;
import com.practice_app.repos.UserRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public QuestionDto createQuestion(QuestionCreateDto dto) {

        UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow();

        QuestionEntity question = new QuestionEntity();
        question.setMessage(dto.getMessage());
        question.setCreatedAt(LocalDateTime.now());
        question.setLikesCount(0);
        question.setReplyCount(0);
        question.setUser(user);

        questionRepository.save(question);

        return convertToDto(question);
    }

    public List<QuestionDto> getAllQuestions() {

        return questionRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    private QuestionDto convertToDto(QuestionEntity q) {

        QuestionDto dto = new QuestionDto();

        dto.setId(q.getId());
        dto.setMessage(q.getMessage());
        dto.setLikesCount(q.getLikesCount());
        dto.setReplyCount(q.getReplyCount());
        dto.setUsername(q.getUser().getUsername());

        return dto;
    }
}