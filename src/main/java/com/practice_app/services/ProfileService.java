package com.practice_app.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.ProfileResponseDto;
import com.practice_app.models.ClgEntity;
import com.practice_app.models.MentorEntity;
import com.practice_app.models.StaffEntity;
import com.practice_app.models.StdEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ProfileResponseDto getProfile(Long userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Object profile = null;

        switch (user.getRole()) {

            case "STD":
                StdEntity std = user.getStudent();
                profile = Map.of(
                        "rollno", std.getRollno(),
                        "dept", std.getDept(),
                        "year", std.getYear(),
                        "college", std.getCollege().getClgName()
                );
                break;

            case "MENTOR":
                MentorEntity mentor = user.getMentor();
                profile = Map.of(
                        "expertise", mentor.getExpertise(),
                        "specialization", mentor.getSpecialization(),
                        "college", mentor.getCollege().getClgName()
                );
                break;

            case "STAFF":
                StaffEntity staff = user.getStaff();
                profile = Map.of(
                        "dept", staff.getDept(),
                        "college", staff.getCollege().getClgName()
                );
                break;

            case "PRINCIPAL":
                ClgEntity college = user.getCollege();
                profile = Map.of(
                        "college", college.getClgName(),
                        "clgCode", college.getClgCode()
                );
                break;
        }

        return new ProfileResponseDto(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getRole(),
                profile
        );
    }
}
