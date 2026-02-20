package com.example.Pranay.service;

import com.example.Pranay.entity.Likes;
import com.example.Pranay.entity.Post;
import com.example.Pranay.entity.User;
import com.example.Pranay.repository.LikeRepository;
import com.example.Pranay.repository.PostRepository;
import com.example.Pranay.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public String toggleLike(String username, Long postId) {
        User user =userRepository.findByUsername(username).get(0);
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("Post not found"));

        Optional<Likes> existingLikes =
                likeRepository.findByUserIdAndPostId(user.getId(), postId);

        if (existingLikes.isPresent()) {
            likeRepository.delete(existingLikes.get());
            return "Post unliked";
        }else{
            Likes like = new Likes();
            like.setUser(user);
            like.setPost(post);

            likeRepository.save(like);
            return "Post liked";
        }
    }
    public long getLikeCount(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    public boolean isLiked(String username, Long postId) {
        User user=userRepository.findByUsername(username).get(0);
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("Post not found"));
        return likeRepository.existsByUserIdAndPostId(user.getId(), postId);
    }
}
