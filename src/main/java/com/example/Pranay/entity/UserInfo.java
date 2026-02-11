package com.example.Pranay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfo {
    @Id
    long ID;
    private String name;
    private String profilePic;
    private String phone;
    private String location;

    private VarificationStatus verificationStatus;

    @OneToOne
    User user;
}
