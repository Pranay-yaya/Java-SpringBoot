package com.example.Pranay.controller;

import com.example.Pranay.entity.Comment;
import com.example.Pranay.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private  CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment>addComment(
            @RequestParam Long userId,
            @RequestParam Long postId,
            @RequestParam String content){
        Comment comment=commentService.addComment(userId,postId,content);
        return ResponseEntity.ok(comment);
    }
}
