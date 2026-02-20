package com.example.Pranay.controller;

import com.example.Pranay.entity.Comment;
import com.example.Pranay.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class CommentController {

    private final CommentService commentService;

    // ✅ Add Comment
    @PostMapping
    public ResponseEntity<Comment> addComment(
            @RequestParam Long postId,
            @RequestParam String content,
            Authentication authentication) {

        String username = authentication.getName();
        Comment comment = commentService.addComment(username, postId, content);

        return ResponseEntity.ok(comment);
    }

    // ✅ Update Comment
    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long commentId,
            @RequestParam String content,
            Authentication authentication) {

        String username = authentication.getName();
        Comment updated = commentService.updateComment(commentId, username, content);

        return ResponseEntity.ok(updated);
    }

    // ✅ Delete Comment (FIXED → DELETE mapping)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            Authentication authentication) {

        String username = authentication.getName();
        commentService.deleteComment(commentId, username);

        return ResponseEntity.ok("Comment deleted successfully");
    }

    // ✅ Get all comments of a post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {

        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    // ✅ Count comments of a post
    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> countComments(@PathVariable Long postId) {

        return ResponseEntity.ok(commentService.countComments(postId));
    }
}
