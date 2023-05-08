package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.Comment;
import com.sparta.hanghae66.entity.Post;
import com.sparta.hanghae66.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    Optional<Post> findByPostId(Long postId);

    Optional<Post> findByPostUserId(String postUserId);

    @Query("SELECT p FROM TB_POST p WHERE p.postUserId = :postUserId")
    List<Post> findAllPostByPostUserId(@Param("postUserId") String postUserId);
}
