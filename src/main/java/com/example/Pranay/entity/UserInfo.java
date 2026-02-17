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
    private long ID;
    private String name;
    private String profilePic;
    private String phone;
    private String location;

    private VarificationStatus verificationStatus;

    @OneToOne
    @JoinColumn(name="user_id",unique = true)

    User user;
}
