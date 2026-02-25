package com.example.Pranay.repository;

import com.example.Pranay.entity.Follow;
import com.example.Pranay.entity.FollowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Optional<Follow> findByFollowerIdAndFollowingId(Long userId, Long followingId);

    List<Follow> findByFollowerIdAndFollowStatus(long follower_id, FollowStatus followStatus);

    List<Follow> findByFollowingIdAndFollowStatus(long following_id, FollowStatus followStatus);

    boolean existsByFollowerIdAndFollowingId(Long follower_id, Long following_id);

}
