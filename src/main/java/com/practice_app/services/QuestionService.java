package com.practice_app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.QuestionRequestDto;
import com.practice_app.dtos.QuestionResponseDto;
import com.practice_app.dtos.ReplyRequestDto;
import com.practice_app.dtos.ReplyResponseDto;
import com.practice_app.models.QuestionEntity;
import com.practice_app.models.QuestionLikeEntity;
import com.practice_app.models.ReplyEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.QuestionLikeRepository;
import com.practice_app.repos.QuestionRepository;
import com.practice_app.repos.ReplyRepository;
import com.practice_app.repos.UserRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionLikeRepository likeRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE QUESTION
    public QuestionResponseDto createQuestion(QuestionRequestDto dto) {

        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        QuestionEntity q = new QuestionEntity();

        q.setMessage(dto.getMessage());
        q.setLikesCount(0);
        q.setReplyCount(0);
        q.setCreatedAt(LocalDateTime.now());
        q.setUser(user);

        QuestionEntity saved = questionRepository.save(q);

        return convertToDto(saved);
    }

    // GET ALL QUESTIONS
    public List<QuestionResponseDto> getAllQuestions() {

        List<QuestionEntity> questions =
                questionRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return questions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // LIKE QUESTION
    public void likeQuestion(Long questionId, Long userId) {

        if (likeRepository.findByQuestionIdAndUserId(questionId, userId).isPresent()) {
            return; // already liked
        }

        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        QuestionLikeEntity like = new QuestionLikeEntity();
        like.setQuestion(question);
        like.setUser(user);

        likeRepository.save(like);

        question.setLikesCount(question.getLikesCount() + 1);
        questionRepository.save(question);
    }

    // REPLY QUESTION
    public void replyQuestion(Long questionId, ReplyRequestDto dto) {

        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ReplyEntity reply = new ReplyEntity();

        reply.setMessage(dto.getMessage());
        reply.setCreatedAt(LocalDateTime.now());
        reply.setQuestion(question);
        reply.setUser(user);

        replyRepository.save(reply);

        question.setReplyCount(question.getReplyCount() + 1);
        questionRepository.save(question);
    }

    // CONVERT ENTITY → DTO
    private QuestionResponseDto convertToDto(QuestionEntity q) {

        QuestionResponseDto dto = new QuestionResponseDto();

        dto.setId(q.getId());
        dto.setMessage(q.getMessage());
        dto.setLikesCount(q.getLikesCount());
        dto.setReplyCount(q.getReplyCount());
        dto.setCreatedAt(q.getCreatedAt());
        dto.setUsername(q.getUser().getUsername());
        dto.setRole(q.getUser().getRole());

        return dto;
    }
    
    public List<ReplyResponseDto> getReplies(Long questionId) {

        List<ReplyEntity> replies = replyRepository.findByQuestionId(questionId);

        return replies.stream()
                .map(this::convertReplyToDto)
                .toList();
    }
    
    private ReplyResponseDto convertReplyToDto(ReplyEntity reply) {

        ReplyResponseDto dto = new ReplyResponseDto();

        dto.setId(reply.getId());
        dto.setMessage(reply.getMessage());
        dto.setCreatedAt(reply.getCreatedAt());

        dto.setUsername(reply.getUser().getUsername());
        dto.setRole(reply.getUser().getRole());

        return dto;
    }
}