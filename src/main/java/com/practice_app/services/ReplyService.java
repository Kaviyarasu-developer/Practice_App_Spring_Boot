package com.practice_app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserRepository UserRepository;
    
    @Autowired
    private ReplyLikeRepository replyLikeRepository;

    public ReplyResponseDto addReply(ReplyCreateDto dto) {

        QuestionEntity question = questionRepository.findById(dto.getQuestionId()).orElseThrow();
        UserEntity user = UserRepository.findById(dto.getUserId()).orElseThrow();

        ReplyEntity reply = new ReplyEntity();
        reply.setMessage(dto.getMessage());
        reply.setCreatedAt(LocalDateTime.now());
        reply.setQuestion(question);
        reply.setUser(user);

        replyRepository.save(reply);

        question.setReplyCount(question.getReplyCount() + 1);
        questionRepository.save(question);

        return convertToDto(reply, dto.getUserId());

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

        // ✅ likes count
        dto.setLikesCount(
            replyLikeRepository.countByReplyId(r.getId())
        );

        // ✅ isLiked (SAFE)
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

        ReplyEntity reply =
            replyRepository.findById(replyId).orElseThrow();

        QuestionEntity question = reply.getQuestion();

        // 🔥 delete reply first
        replyRepository.delete(reply);

        // 🔥 decrement count safely
        int currentCount = question.getReplyCount();

        if(currentCount > 0){
            question.setReplyCount(currentCount - 1);
            questionRepository.save(question);
        }
    }
    
        public void toggleReplyLike(Long replyId, Long userId){

            Optional<ReplyLikeEntity> existing =
                replyLikeRepository.findByReplyIdAndUserId(replyId, userId);

            if(existing.isPresent()){
                // 🔥 UNLIKE
                replyLikeRepository.delete(existing.get());
            } else {
                // 🔥 LIKE
                ReplyEntity reply = replyRepository.findById(replyId).orElseThrow();
                UserEntity user = UserRepository.findById(userId).orElseThrow();

                ReplyLikeEntity like = new ReplyLikeEntity();
                like.setReply(reply);
                like.setUser(user);

                replyLikeRepository.save(like);
            }
        }
}
