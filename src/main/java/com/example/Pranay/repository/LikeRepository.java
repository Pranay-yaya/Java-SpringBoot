package com.example.Pranay.repository;

import com.example.Pranay.entity.Likes;
import com.example.Pranay.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes,Long> {
    public List<Likes> findAllByUserId(Long id);

    Optional<Likes> findByUserIdAndPostId(Long userId, Long postId);

    long countByPostId(Long postId);

    Optional<Likes> findByUserAndPost(Long userId,Long postId);

    boolean existsByUserIdAndPostId(Long userId, Long postId);

}
