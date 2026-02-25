package com.example.Pranay.service;

import com.example.Pranay.dto.PostResponseDto;
import com.example.Pranay.entity.Post;
import com.example.Pranay.entity.User;
import com.example.Pranay.repository.PostRepository;
import com.example.Pranay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    private PostResponseDto toDto(Post post){
        PostResponseDto dto = new PostResponseDto();
        dto.setUsername(post.getUser().getUsername());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setMediaUrl(post.getMediaUrl());
        dto.setLikeCount(post.getLikes().size());
        dto.setCommentsCount(post.getComments().size());
        return dto;
    }

    public Page<PostResponseDto> getFeed(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size , Sort.by("createdAt").descending());
        User user =  userRepository.findByUsername(username).get();
        Page<Post> posts  = postRepository.findFeed(user.getId(), pageable);
        return posts.map(this::toDto) ;
    }
}
