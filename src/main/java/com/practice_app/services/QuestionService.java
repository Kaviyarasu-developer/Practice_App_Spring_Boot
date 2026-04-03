package com.practice_app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice_app.dtos.DeleteEventDto;
import com.practice_app.dtos.QuestionCreateDto;
import com.practice_app.dtos.QuestionResponseDto;
import com.practice_app.models.QuestionEntity;
import com.practice_app.models.QuestionLikeEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.QuestionLikeRepository;
import com.practice_app.repos.QuestionRepository;
import com.practice_app.repos.UserRepository;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private  QuestionLikeRepository likeRepository;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public QuestionResponseDto createQuestion(QuestionCreateDto dto) {

        UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow();

        QuestionEntity question = new QuestionEntity();
        question.setMessage(dto.getMessage());
        question.setCreatedAt(LocalDateTime.now());
        question.setLikesCount(0);
        question.setReplyCount(0);
        question.setUser(user);

        QuestionEntity saved = questionRepository.save(question);

        QuestionResponseDto response = convertToDto(saved, dto.getUserId());

        messagingTemplate.convertAndSend("/topic/questions", response);

        return response;
    }

    public List<QuestionResponseDto> getAllQuestions(Long userId){
 
        return questionRepository.findAll()
                .stream()
                .map(q -> convertToDto(q, userId))
                .toList();
    }
    

    public void deleteQuestion(Long id) {

        questionRepository.deleteById(id);

        messagingTemplate.convertAndSend(
            "/topic/questions",
            new DeleteEventDto(id, "DELETE")
        );
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
        UserEntity user = userRepository.findById(userId).orElseThrow();

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