package com.practice_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.models.QuestionEntity;
import com.practice_app.models.QuestionLikeEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.QuestionLikeRepository;
import com.practice_app.repos.QuestionRepository;
import com.practice_app.repos.UserRepository;

@Service
public class QuestionLikeService {

    @Autowired
    private QuestionLikeRepository likeRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public void likeQuestion(Long questionId, Long userId) {

        if (likeRepository.existsByQuestion_IdAndUser_Id(questionId, userId))
            return;

        QuestionEntity question = questionRepository.findById(questionId).orElseThrow();
        UserEntity user = userRepository.findById(userId).orElseThrow();

        QuestionLikeEntity like = new QuestionLikeEntity();
        like.setQuestion(question);
        like.setUser(user);

        likeRepository.save(like);

        question.setLikesCount(question.getLikesCount() + 1);
        questionRepository.save(question);
    }

    public void unlikeQuestion(Long questionId, Long userId) {

        QuestionLikeEntity like =
                likeRepository.findByQuestion_IdAndUser_Id(questionId, userId);

        if (like != null) {
            likeRepository.delete(like);

            QuestionEntity question = questionRepository.findById(questionId).orElseThrow();
            question.setLikesCount(question.getLikesCount() - 1);
            questionRepository.save(question);
        }
    }
}