package com.practice_app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.QuestionCreateDto;
import com.practice_app.dtos.QuestionResponseDto;
import com.practice_app.models.QuestionEntity;
import com.practice_app.models.QuestionLikeEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.QuestionLikeRepository;
import com.practice_app.repos.QuestionRepository;
import com.practice_app.repos.UserRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository UserRepository;
    
    @Autowired
    private  QuestionLikeRepository likeRepository;

    public QuestionResponseDto createQuestion(QuestionCreateDto dto) {

        UserEntity user = UserRepository.findById(dto.getUserId()).orElseThrow();

        QuestionEntity question = new QuestionEntity();
        question.setMessage(dto.getMessage());
        question.setCreatedAt(LocalDateTime.now());
        question.setLikesCount(0);
        question.setReplyCount(0);
        question.setUser(user);

        questionRepository.save(question);

        return convertToDto(question,dto.getUserId());
    }

    public List<QuestionResponseDto> getAllQuestions(Long userId){
 
        return questionRepository.findAll()
                .stream()
                .map(q -> convertToDto(q, userId))
                .toList();
    }
    

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    private QuestionResponseDto convertToDto(QuestionEntity question, Long userId){

        QuestionResponseDto dto = new QuestionResponseDto();
        
        dto.setUserId(question.getUser().getId());
        dto.setId(question.getId());
        dto.setMessage(question.getMessage());
        dto.setLikesCount(question.getLikesCount());
        dto.setReplyCount(question.getReplyCount());
        System.out.println(question.getReplyCount());

        dto.setUsername(question.getUser().getUsername());
        dto.setRole(question.getUser().getRole());

        /// ⭐ CHECK LIKE
        boolean isLiked = likeRepository
                .existsByQuestion_IdAndUser_Id(question.getId(), userId);
        dto.setLiked(isLiked);

        return dto;
    }
    
    public void toggleLike(Long questionId, Long userId){

        QuestionLikeEntity existing =
                likeRepository.findByQuestion_IdAndUser_Id(questionId, userId);

        QuestionEntity question = questionRepository.findById(questionId).orElseThrow();
        UserEntity user = UserRepository.findById(userId).orElseThrow();

        if(existing != null){
            // UNLIKE 
            likeRepository.delete(existing);

            question.setLikesCount(question.getLikesCount() - 1);

        } else {
            // LIKE
            QuestionLikeEntity like = new QuestionLikeEntity();
            like.setQuestion(question);
            like.setUser(user);

            likeRepository.save(like);

            question.setLikesCount(question.getLikesCount() + 1);
        }

        questionRepository.save(question);
    }
}