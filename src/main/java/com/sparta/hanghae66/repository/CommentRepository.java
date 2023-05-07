package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.Comment;
import com.sparta.hanghae66.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM TB_COMMENT c WHERE c.cmtUserId = :userId ")
    List<Comment> findByCommentUserId(@Param("userId") String userId);
}