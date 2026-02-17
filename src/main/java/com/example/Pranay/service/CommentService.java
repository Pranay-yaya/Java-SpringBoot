package com.example.Pranay.service;

import com.example.Pranay.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // instead of Autowired
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
}
