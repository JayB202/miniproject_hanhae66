package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
}
