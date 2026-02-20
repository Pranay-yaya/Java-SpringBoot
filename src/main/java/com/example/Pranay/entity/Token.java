package com.example.Pranay.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    private String token;
    LocalDateTime expiryDate;

    @OneToOne(cascade = CascadeType.ALL)
    User user;

}
