package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    Optional<CommentLikes> findByUsernameAndCommentId(String userId, Long commentId);
    Long countByUsernameAndCommentId(String userId, Long commentId);
}
