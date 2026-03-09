package com.practice_app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.ReplyCreateDto;
import com.practice_app.dtos.ReplyDto;
import com.practice_app.models.QuestionEntity;
import com.practice_app.models.ReplyEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.QuestionRepository;
import com.practice_app.repos.ReplyRepository;
import com.practice_app.repos.UserRepository;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public ReplyDto addReply(ReplyCreateDto dto) {

        QuestionEntity question = questionRepository.findById(dto.getQuestionId()).orElseThrow();
        UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow();

        ReplyEntity reply = new ReplyEntity();
        reply.setMessage(dto.getMessage());
        reply.setCreatedAt(LocalDateTime.now());
        reply.setQuestion(question);
        reply.setUser(user);

        replyRepository.save(reply);

        question.setReplyCount(question.getReplyCount() + 1);
        questionRepository.save(question);

        return convertToDto(reply);
    }

    public List<ReplyDto> getReplies(Long questionId) {

        return replyRepository.findByQuestion_Id(questionId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private ReplyDto convertToDto(ReplyEntity r) {

        ReplyDto dto = new ReplyDto();

        dto.setId(r.getId());
        dto.setMessage(r.getMessage());
        dto.setUsername(r.getUser().getUsername());
        dto.setCreatedAt(r.getCreatedAt());

        return dto;
    }
}