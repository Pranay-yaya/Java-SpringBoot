package com.example.Pranay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private String mediaUrl;
}
