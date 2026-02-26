package com.example.Pranay.service;

import com.example.Pranay.entity.Follow;
import com.example.Pranay.entity.FollowStatus;
import com.example.Pranay.entity.User;
import com.example.Pranay.repository.FollowRepository;
import com.example.Pranay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;   // <-- was missing @Autowired

    public List<User> getFollowers(String username) {

        User user = userRepository.findByUsername(username).get();

        List<Follow> follows = followRepository.findByFollowingIdAndFollowStatus(user.getId(), FollowStatus.ACCEPTED);

        return follows.stream().map(Follow::getFollower).collect(Collectors.toList());
    }


    public List<User> getFollowing(String username) {
        User user = userRepository.findByUsername(username).get();

        List<Follow> follows = followRepository.findByFollowerIdAndFollowStatus(user.getId(), FollowStatus.ACCEPTED);

        return follows.stream().map(Follow::getFollowing).collect(Collectors.toList());
    }


    public List<Follow> getPendingRequests(String username) {

        User user = userRepository.findByUsername(username).get();

        return followRepository.findByFollowingIdAndFollowStatus(user.getId(), FollowStatus.PENDING);
    }

    @Transactional
    public String toggleFollow(Long targetUserId, String currentUsername) {

        User currentUser = userRepository.findByUsername(currentUsername).get();

        User targetUser = userRepository.findById(targetUserId).get();

        if (currentUser.getId() == targetUser.getId()) {

            throw new RuntimeException("Cannot follow yourself");
        }

        var existingOpt = followRepository.findByFollowerIdAndFollowingId(currentUser.getId(), targetUser.getId());
        if (existingOpt.isPresent()) {
            Follow existing = existingOpt.get();
            followRepository.delete(existing);

            return existing.getFollowStatus() == FollowStatus.ACCEPTED ? "Unfollowed" : "Follow request cancelled";
        }

        Follow follow = new Follow();

        follow.setFollower(currentUser);

        follow.setFollowing(targetUser);

        boolean isPrivate = targetUser.getUserInfo().isPrivate();

        follow.setFollowStatus(isPrivate ? FollowStatus.PENDING : FollowStatus.ACCEPTED);

        followRepository.save(follow);

        return isPrivate ? "Follow request sent" : "Followed";
    }

    @Transactional
    public String acceptFollowRequest(Long followId, String currentUsername) {

        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new RuntimeException("Follow request not found"));

        if (!follow.getFollowing().getUsername().equals(currentUsername)) {
            throw new RuntimeException("Not authorized");
        }

        if (follow.getFollowStatus() != FollowStatus.PENDING) {
            throw new RuntimeException("Request is not pending");
        }

        follow.setFollowStatus(FollowStatus.ACCEPTED);

        followRepository.save(follow);

        return "Follow request accepted";
    }

    @Transactional
    public String rejectFollowRequest(Long followId, String currentUsername) {

        Follow follow = followRepository.findById(followId)

                .orElseThrow(() -> new RuntimeException("Follow request not found"));

        if (!follow.getFollowing().getUsername().equals(currentUsername)) {

            throw new RuntimeException("Not authorized");
        }


        if (follow.getFollowStatus() != FollowStatus.PENDING) {

            throw new RuntimeException("Request is not pending");
        }

        followRepository.delete(follow);

        return "Follow request rejected";
    }

    public boolean isFollowing(String currentUsername, Long targetUserId) {

        User currentUser = userRepository.findByUsername(currentUsername).get();

        return followRepository.findByFollowerIdAndFollowingId(currentUser.getId(), targetUserId)

                .map(f -> f.getFollowStatus() == FollowStatus.ACCEPTED)
                .orElse(false);
    }

    public long getFollowerCount(Long userId) {

        return followRepository.findByFollowingIdAndFollowStatus(userId, FollowStatus.ACCEPTED).size();
    }


    public long getFollowingCount(Long userId) {

        return followRepository.findByFollowerIdAndFollowStatus(userId, FollowStatus.ACCEPTED).size();
    }



    public List<User> getMutualFriends(Long targetUserId, String currentUsername) {

        User currentUser = userRepository.findByUsername(currentUsername).get();

        User targetUser = userRepository.findById(targetUserId).get();


        Set<Long> currentFollowingIds = followRepository

                .findByFollowerIdAndFollowStatus(currentUser.getId(), FollowStatus.ACCEPTED)

                .stream()
                .map(f -> f.getFollowing().getId())
                .collect(Collectors.toSet());


        List<User> mutual = followRepository.

                findByFollowerIdAndFollowStatus(targetUser.getId(), FollowStatus.ACCEPTED)

                .stream()
                .map(Follow::getFollowing)
                .filter(u -> currentFollowingIds.contains(u.getId()))
                .collect(Collectors.toList());

        return mutual;
    }
}