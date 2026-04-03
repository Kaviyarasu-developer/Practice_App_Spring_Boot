package com.practice_app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.DeleteEventDto;
import com.practice_app.dtos.replydto.ReplyCreateDto;
import com.practice_app.dtos.replydto.ReplyResponseDto;
import com.practice_app.models.QuestionEntity;
import com.practice_app.models.ReplyEntity;
import com.practice_app.models.ReplyLikeEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.QuestionRepository;
import com.practice_app.repos.ReplyLikeRepository;
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
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @Autowired
    private ReplyLikeRepository replyLikeRepository;

    public ReplyResponseDto addReply(ReplyCreateDto dto) {

        QuestionEntity question = questionRepository.findById(dto.getQuestionId()).orElseThrow();
        UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow();

        ReplyEntity reply = new ReplyEntity();
        reply.setMessage(dto.getMessage());
        reply.setCreatedAt(LocalDateTime.now());
        reply.setQuestion(question);
        reply.setUser(user);

        ReplyEntity saved = replyRepository.save(reply);

        question.setReplyCount(question.getReplyCount() + 1);
        questionRepository.save(question);

        ReplyResponseDto response = convertToDto(saved, dto.getUserId());

        messagingTemplate.convertAndSend(
            "/topic/replies/" + dto.getQuestionId(),
            response
        );

        return response;

    }

    public List<ReplyResponseDto> getReplies(Long questionId, Long userId) {

        return replyRepository.findByQuestion_Id(questionId)
                .stream()
                .map(r -> convertToDto(r, userId)) 
                .toList();
    }

    private ReplyResponseDto convertToDto(ReplyEntity r, Long userId) {

        ReplyResponseDto dto = new ReplyResponseDto();

        dto.setId(r.getId());
        dto.setRole(r.getUser().getRole());
        dto.setMessage(r.getMessage());
        dto.setUsername(r.getUser().getUsername());
        dto.setCreatedAt(r.getCreatedAt());
        dto.setUserId(r.getUser().getId());

        dto.setLikesCount(
            replyLikeRepository.countByReplyId(r.getId())
        );

        if (userId != null) {
            dto.setLiked(
                replyLikeRepository
                    .findByReplyIdAndUserId(r.getId(), userId)
                    .isPresent()
            );
        } else {
            dto.setLiked(false);
        }

        return dto;
    }
    
    public void deleteReply(Long replyId){

        ReplyEntity reply = replyRepository.findById(replyId)
                .orElseThrow();

        QuestionEntity question = reply.getQuestion();

        replyRepository.delete(reply);

        int currentCount = question.getReplyCount();

        if(currentCount > 0){
            question.setReplyCount(currentCount - 1);
            questionRepository.save(question);
        }

        messagingTemplate.convertAndSend(
            "/topic/replies/" + question.getId(),
            new DeleteEventDto(replyId, "DELETE")
        );
    }
    
        public void toggleReplyLike(Long replyId, Long userId){

            Optional<ReplyLikeEntity> existing =
                replyLikeRepository.findByReplyIdAndUserId(replyId, userId);

            if(existing.isPresent()){
                replyLikeRepository.delete(existing.get());
            } else {
                ReplyEntity reply = replyRepository.findById(replyId).orElseThrow();
                UserEntity user = userRepository.findById(userId).orElseThrow();

                ReplyLikeEntity like = new ReplyLikeEntity();
                like.setReply(reply);
                like.setUser(user);

                replyLikeRepository.save(like);
            }
        }
}
