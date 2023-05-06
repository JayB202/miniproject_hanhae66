package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    Optional<PostLikes> findByUsernameAndPostId(String userId, Long postId);
    Long countByUsernameAndPostId(String userId, Long postId);
}
