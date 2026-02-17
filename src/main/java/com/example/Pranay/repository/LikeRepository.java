package com.example.Pranay.repository;

import com.example.Pranay.entity.Likes;
import com.example.Pranay.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes,Long> {
    public List<Likes> findAllByUserId(Long userId);
}
