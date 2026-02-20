package com.example.Pranay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    List<Likes> likes  = new ArrayList<>() ;

    public void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setUser(null);
    }

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    private String verificationToken;

    private LocalDateTime tokenExpiry;






}