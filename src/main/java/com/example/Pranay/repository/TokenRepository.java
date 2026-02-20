package com.example.Pranay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Pranay.entity.Token;


public interface TokenRepository extends JpaRepository<Token,Long> {

    Token findByToken(String token);
}
