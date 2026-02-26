package com.example.Pranay.repository;

import com.example.Pranay.entity.Follow;
import com.example.Pranay.entity.FollowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowerIdAndFollowingId(Long followerId, Long followingId);

    List<Follow> findByFollowerIdAndFollowStatus(Long followerId, FollowStatus status);

    List<Follow> findByFollowingIdAndFollowStatus(Long followingId, FollowStatus status);


    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
}