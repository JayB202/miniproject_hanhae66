package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    Optional<PostLikes> findByPostLikesUserIdAndPostLikesId(String userId, Long postId);
    @Query("SELECT count(p) FROM TB_POSTLIKES p WHERE p.postLikesUserId = :userId AND p.postLikesId = :postId ")
    Long getPostLikesCount(@Param("userId") String userId, @Param("postId") Long postId);
}
