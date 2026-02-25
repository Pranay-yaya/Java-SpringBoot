package com.example.Pranay.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class GlobalSearchResponseDto {
    private Page<UserResponseDto> users;
    private Page<PostResponseDto>posts;
}
