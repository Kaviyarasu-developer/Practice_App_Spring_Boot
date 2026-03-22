package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice_app.dtos.community.CommunityCreateDto;
import com.practice_app.dtos.community.CommunityDto;
import com.practice_app.services.CommunityService;

@RestController
@RequestMapping("/app/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;


    @PostMapping("/create")
    public void createCommunity(@RequestBody CommunityCreateDto dto){
        communityService.createCommunity(dto);
    }

    @GetMapping("/mentor")
    public List<CommunityDto> getCommunitiesByMentorId(@RequestParam Long mentorId,
            @RequestParam Long userId){
		return communityService.getAllCommunitybyMentorId(mentorId, userId);
	}

    @GetMapping("/all")
    public List<CommunityDto> getAllCommunities(
            @RequestParam Long userId){
        return communityService.getAllCommunities(userId); 
    }


    @GetMapping("/{id}")
    public CommunityDto getCommunity(@PathVariable Long id,
            @RequestParam Long userId){
        return communityService.getCommunityById(id, userId);
    } 


    @PostMapping("/{communityId}/join")
    public ResponseEntity<?> joinCommunity(
            @PathVariable Long communityId,
            @RequestParam Long userId
    ){
        communityService.joinCommunity(communityId, userId);
        return ResponseEntity.ok("Joined successfully");

    }

    @DeleteMapping("/{communityId}/leave")
    public void leaveCommunity(
            @PathVariable Long communityId,
            @RequestParam Long userId
    ){
        communityService.leaveCommunity(communityId, userId);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteCommunity(@PathVariable Long id){
        communityService.deleteCommunity(id);
    }

}
