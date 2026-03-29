package com.practice_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.practice_app.services.CommunityMemberService;
import com.practice_app.services.CommunityMessageService;
import com.practice_app.services.CommunityService;

@RestController
@RequestMapping("/app/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;
    
    @Autowired
	private CommunityMessageService communityMessageService;
    
    @Autowired
    private CommunityMemberService communityMemberService;


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
    
    @GetMapping("/joined")
    public List<CommunityDto> getJoinedCommunities(
            @RequestParam Long userId){
        return communityService.getJoinedCommunities(userId); 
    }


    @GetMapping("/{id}")
    public CommunityDto getCommunity(@PathVariable Long id,
            @RequestParam Long userId){
        return communityService.getCommunityById(id, userId);
    } 
    
    @PostMapping("/toggle-join")
    public void toggleJoin(
			@RequestParam Long communityId,
			@RequestParam Long userId
	){
		communityMemberService.toggleJoin(communityId, userId);
	}
    
    @GetMapping("/isJoined")
    public boolean isJoined(
            @RequestParam Long communityId,
            @RequestParam Long userId
    ) {
        return communityMemberService.isUserJoined(communityId, userId);
    }
    
    @DeleteMapping("/message/{messageId}")
    public void deleteMessage(
            @PathVariable Long messageId,
            @RequestParam Long userId
    ) {
    	System.out.println("Deleting message with ID: " + messageId + " by user ID: " + userId);
        communityMessageService.deleteMessage(messageId, userId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommunity(@PathVariable Long id){
        communityService.deleteCommunity(id);
    }

}
