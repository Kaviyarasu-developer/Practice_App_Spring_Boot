package com.practice_app.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice_app.dtos.AccountCreateDto;
import com.practice_app.dtos.AccountResponseDto;
import com.practice_app.dtos.UserResponseDto;
import com.practice_app.models.ClgEntity;
import com.practice_app.models.MentorEntity;
import com.practice_app.models.StaffEntity;
import com.practice_app.models.StdEntity;
import com.practice_app.models.UserEntity;
import com.practice_app.repos.ClgRepository;
import com.practice_app.repos.MentorRepository;
import com.practice_app.repos.StaffRepository;
import com.practice_app.repos.StdRepository;
import com.practice_app.repos.UserRepository;

@Service
public class AccountService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private StdRepository stdRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ClgRepository clgRepository;

    public void createAccount(AccountCreateDto dto){

        UserEntity user = new UserEntity();

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        UserRepository.save(user); 

        switch(dto.getRole()){

            case "STD":

                StdEntity std = new StdEntity();

                std.setUser(user);
                std.setRollno(dto.getRollno());
                std.setDept(dto.getDept());

                ClgEntity clg1 =
                        clgRepository.findById(dto.getClgcode()).orElseThrow();

                std.setCollege(clg1);

                stdRepository.save(std);

                break;


            case "STAFF":

                StaffEntity staff = new StaffEntity();

                staff.setUser(user);
                staff.setDept(dto.getDept());
                
               System.out.println(dto.getClgcode());

                ClgEntity clg2 =
                        clgRepository.findById(dto.getClgcode()).orElseThrow();

                staff.setCollege(clg2);

                staffRepository.save(staff);

                break;


            case "MENTOR":

                MentorEntity mentor = new MentorEntity();

                mentor.setUser(user);

                ClgEntity clg3 =
                        clgRepository.findById(dto.getClgcode()).orElseThrow();

                mentor.setCollege(clg3);

                mentorRepository.save(mentor);

                break;


            case "PRINCIPAL":

                ClgEntity clg = new ClgEntity();
                
                clg.setClgCode(dto.getClgcode());
                clg.setClgName(dto.getClgname());
                clg.setUser(user);

                clgRepository.save(clg);

                break;
        }

    }

    public List<UserResponseDto> getAllAccountsByRole(String role) {

        switch(role){

            case "STD":

                return stdRepository.findAll()
                        .stream()
                        .map(std -> {

                            UserResponseDto dto = convertToDto(std.getUser());

                            dto.setClgname(std.getCollege().getClgName());
                            dto.setClgcode(std.getCollege().getClgCode());

                            return dto;

                        })
                        .toList();


            case "STAFF":

                return staffRepository.findAll()
                        .stream()
                        .map(staff -> {

                            UserResponseDto dto = convertToDto(staff.getUser());

                            dto.setClgname(staff.getCollege().getClgName());
                            dto.setClgcode(staff.getCollege().getClgCode());
                            dto.setDept(staff.getDept());

                            return dto;

                        })
                        .toList();


            case "MENTOR":

                return mentorRepository.findAll()
                        .stream()
                        .map(mentor -> {

                            UserResponseDto dto = convertToDto(mentor.getUser());

                            dto.setClgname(mentor.getCollege().getClgName());
                            dto.setClgcode(mentor.getCollege().getClgCode());

                            return dto;

                        })
                        .toList();


            case "PRINCIPAL":

                return clgRepository.findAll()
                        .stream()
                        .map(clg -> {

                            UserResponseDto dto = convertToDto(clg.getUser());

                            dto.setClgname(clg.getClgName());
                            dto.setClgcode(clg.getClgCode());

                            return dto;

                        })
                        .toList();


            default:
                throw new RuntimeException("Invalid role");
        }
    }
        
    private UserResponseDto convertToDto(UserEntity user){

            if(user == null){
                return null;
            }

            UserResponseDto dto = new UserResponseDto();

            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setUsername(user.getUsername());
            dto.setRole(user.getRole());

            return dto;
   }
    
    public AccountResponseDto getAccount(Long userId) {

        UserEntity user = UserRepository.findById(userId)
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

        return new AccountResponseDto(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getRole(),
                profile
        );
    }

}