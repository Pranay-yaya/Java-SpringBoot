package com.example.Pranay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;


    private boolean isPrivate = false;

    private String name;
    private String profilePic;
    private String phone;
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;   // ROLE_USER, ROLE_ADMIN

    @OneToOne
    @JoinColumn(name="user_id",unique = true)
    User user;

    public void setVerificationStatus(VerificationStatus verificationStatus) {
    }
}
