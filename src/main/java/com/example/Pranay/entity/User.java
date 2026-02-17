package com.example.Pranay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(unique = true , nullable = false)
    private String username ;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false , unique = true)
    private String email;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    UserInfo userInfo;
    @Enumerated(EnumType.STRING)
    private VerificationStatus   verificationStatus;

    private String verificationToken;

    private LocalDateTime tokenExpiry;

    @Enumerated(EnumType.STRING)
    private Role role;   // ROLE_USER, ROLE_ADMIN




}