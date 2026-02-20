package com.example.Pranay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String content;
    private String mediaUrl ;
    private LocalDateTime createdAt ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @OneToMany(cascade = CascadeType.ALL)
    List<Likes> likes  = new ArrayList<>() ;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
