package com.example.Pranay.service;

import com.example.Pranay.entity.Follow;
import com.example.Pranay.entity.FollowStatus;
import com.example.Pranay.entity.User;
import com.example.Pranay.repository.FollowRepository;
import com.example.Pranay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    FollowRepository followRepository;
    UserRepository userRepository;

    public List<User> getFollowers(String username)
    {
        User user = userRepository.findByUsername(username).get();
        List<Follow> followers = followRepository.findByFollowingIdAndFollowStatus(user.getId() , FollowStatus.FOLLOWING) ;

        List<User> users = new ArrayList<>() ;

        for(Follow follower : followers)
        {
            users.add(follower.getFollower());
        }

        return users;

    }

    public List<User> getFollowing(String username)
    {
        User user = userRepository.findByUsername(username).get();
        List<Follow> followers = followRepository.findByFollowerIdAndFollowStatus(user.getId() , FollowStatus.FOLLOWING) ;

        List<User> users = new ArrayList<>() ;

        for(Follow follower : followers)
        {
            users.add(follower.getFollowing());
        }

        return users;
    }

    public void toggleFollow(long userId , String username){
        User follower = userRepository.findByUsername(username).get();
        User following = userRepository.findById(userId).get();

        Optional<Follow> isfollow = followRepository.findByFollowerIdAndFollowingId(follower.getId(), following.getId());
        if(isfollow.isPresent()){
            followRepository.delete(isfollow.get());
            return ;
        }
        Follow follow = new Follow();
        follow.setFollowing(following);
        follow.setFollower(follower);
        follow.setFollowStatus(FollowStatus.PENDING);
        followRepository.save(follow) ;

    }
}
