package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.DeletedPost;
import com.sparta.hanghae66.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DelPostRepository extends JpaRepository<DeletedPost, Long> {
    List<DeletedPost> findAllByOrderByModifiedAtDesc();
    Optional<DeletedPost> findByDelPostId(Long delPostId);

    Optional<DeletedPost> findByPostUserId(String postUserId);

    @Query("SELECT p FROM TB_DELPOST p WHERE p.postUserId = :postUserId")
    List<DeletedPost> findAllPostByPostUserId(@Param("postUserId") String postUserId);
}
