package com.sparta.hanghae66.service;

import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.entity.*;
import com.sparta.hanghae66.repository.CommentLikesRepository;
import com.sparta.hanghae66.repository.CommentRepository;
import com.sparta.hanghae66.repository.PostLikesRepository;
import com.sparta.hanghae66.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;

    private final PostLikesRepository postLikesRepository;

    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    public ResponseDto postLikeService(Long postId, User user) {
        Post post = postCheck(postId);

        Optional<PostLikes> likesCheck = postLikesRepository.findByPostLikesUserIdAndPostLikesId(user.getId(), postId);

        if (likesCheck.isPresent()) {
            PostLikes postLikes = postLikesCheck(user.getId(), postId);
            boolean likeChk = postLikes.isPostLikes();
            postLikes.setPostLikes(!likeChk);
            postLikesRepository.save(postLikes);
        } else {
            PostLikes postLikes = new PostLikes(post.getPostId(), post.getPostUserName(), post.getPostUserId(), true);
            postLikesRepository.save(postLikes);
        }
        long likes = postLikesRepository.getPostLikesCount(postId);
        post.setPostLikes(likes);
        postRepository.save(post);

        return new ResponseDto("좋아요!", HttpStatus.OK);
    }

    @Transactional
    public ResponseDto commentLikeService(Long commentId, User user) {
        Comment comment = commentCheck(commentId);

        Optional<CommentLikes> likesCheck = commentLikesRepository.findByCmtLikesUserIdAndCmtLikesId(comment.getCmtUserId(), commentId);

        if (likesCheck.isPresent()) {
            CommentLikes commentLikes = commentLikesCheck(comment.getCmtUserId(), commentId);
            boolean likeChk = commentLikes.isCmtLikes();
            commentLikes.setCmtLikes(!likeChk);
            commentLikesRepository.save(commentLikes);
        } else {
            CommentLikes commentLikes = new CommentLikes(comment.getCmtId(), comment.getCmtUserName(), comment.getCmtUserId(), true);
            commentLikesRepository.save(commentLikes);
        }
        long likes = commentLikesRepository.getCmtLikesCount(commentId);
        comment.setCmtLikes(likes);
        commentRepository.save(comment);
        return new ResponseDto("좋아요!", HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public CommentLikes commentLikesCheck(String cmtLikesUserId, Long cmtLikesId) {
        return commentLikesRepository.findByCmtLikesUserIdAndCmtLikesId(cmtLikesUserId, cmtLikesId).orElseThrow(
                () -> new IllegalArgumentException("찾을 수 없는 좋아요 입니다.")
        );
    }

    @Transactional
    public Post postCheck(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다.")
        );
    }

    @Transactional
    public Comment commentCheck(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );
    }

    @Transactional
    public PostLikes postLikesCheck(String userId, Long postId) {
        return postLikesRepository.findByPostLikesUserIdAndPostLikesId(userId, postId).orElseThrow(
                () -> new IllegalArgumentException("찾을 수 없는 좋아요 입니다.")
        );
    }
}





