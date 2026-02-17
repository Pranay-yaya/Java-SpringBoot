package com.example.Pranay.repository;

import com.example.Pranay.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    public List<Post>findAllByUserId(Long userId);
}
