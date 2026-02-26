package com.example.Pranay.controller;

import com.example.Pranay.entity.Follow;
import com.example.Pranay.entity.User;
import com.example.Pranay.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/followers")
    public List<User> getFollowers(Authentication authentication) {

        return followService.getFollowers(authentication.getName());
    }

    @GetMapping("/following")
    public List<User> getFollowing(Authentication authentication) {

        return followService.getFollowing(authentication.getName());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> toggleFollow(@PathVariable Long userId, Authentication authentication) {

        String result = followService.toggleFollow(userId, authentication.getName());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/requests")
    public List<Follow> getPendingRequests(Authentication authentication) {

        return followService.getPendingRequests(authentication.getName());
    }

    @PostMapping("/accept/{followId}")
    public ResponseEntity<String> acceptRequest(@PathVariable Long followId, Authentication authentication) {

        String result = followService.acceptFollowRequest(followId, authentication.getName());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/reject/{followId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long followId, Authentication authentication) {

        String result = followService.rejectFollowRequest(followId, authentication.getName());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/status/{targetUserId}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable Long targetUserId, Authentication authentication) {

        boolean status = followService.isFollowing(authentication.getName(), targetUserId);

        return ResponseEntity.ok(status);
    }

    @GetMapping("/count/followers/{userId}")
    public ResponseEntity<Long> getFollowerCount(@PathVariable Long userId) {

        return ResponseEntity.ok(followService.getFollowerCount(userId));
    }

    @GetMapping("/count/following/{userId}")
    public ResponseEntity<Long> getFollowingCount(@PathVariable Long userId) {

        return ResponseEntity.ok(followService.getFollowingCount(userId));
    }


    @GetMapping("/mutual/{targetUserId}")
    public ResponseEntity<List<User>> getMutualFriends(@PathVariable Long targetUserId, Authentication authentication) {

        List<User> mutual = followService.getMutualFriends(targetUserId, authentication.getName());

        return ResponseEntity.ok(mutual);
    }
}