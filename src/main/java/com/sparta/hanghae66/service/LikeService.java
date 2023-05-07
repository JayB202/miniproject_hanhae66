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

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;

    private final PostLikesRepository postLikesRepository;

    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    public ResponseDto postLikeService(Long postId, User user) {
//        Post post = postCheck(postId);
//
//        Optional<PostLikes> likesCheck = postLikesRepository.findByUsernameAndPostId(String.valueOf(user.getId()), postId);
//
//        if (likesCheck.isPresent()) {
//            PostLikes postLikes = postLikesCheck(String.valueOf(user.getId()), postId);
//            boolean likeChk = post.getPostLikes();
//            postLikes.setPostLikes(!likesCheck.get().isLikes());
//        } else {
//            PostLikes postLikes = new PostLikes(postId, user.getUsername(), true);
//            postLikesRepository.save(postLikes);
//        }
//        long likes = postLikesRepository.countByUsernameAndPostId(user.getUsername(), postId);
//        post.postCountLikes(likes);

        return new ResponseDto("좋아요!", HttpStatus.OK);
    }

    public ResponseDto commentLikeService(Long commentId, User user) {
//        Comment comment = commentCheck(commentId);
//
//        Optional<CommentLikes> likesCheck = commentLikesRepository.findByUsernameAndCommentId(String.valueOf(user.getId()), commentId);
//
//        if (likesCheck.isPresent()) {
//            CommentLikes commentLikes = commentLikesCheck(String.valueOf(user.getId()), commentId);
//            boolean likeChk = comment.getCmtLikes();
//            commentLikes.setCmtLikes();
//        } else {
//            CommentLikes commentLikes = new CommentLikes(commentId, user.getUsername(), true);
//            commentLikesRepository.save(commentLikes);
//        }
//        long likes = commentLikesRepository.countByUsernameAndCommentId(user.getUsername(), commentId);
//        comment.commentCountLikes(likes);

//        if(likesCheck.isPresent()) {
//            postLikesRepository.update(likesCheck.get());
//        } else {
//            CommentLikes commentLikes = new CommentLikes(commentId, user.getUsername(), true, postId);
//            postLikesRepository.save(postLikes);
//        }

        return new ResponseDto("좋아요!", HttpStatus.OK);
    }

//    @Transactional(readOnly = true)
//    public CommentLikes commentLikesCheck(String cmtLikesUserId, Long cmtLikesId) {
//        return commentLikesRepository.findByCmtLikesUserIdAndCmtLikesId(cmtLikesUserId, cmtLikesId).orElseThrow(
//                () -> new IllegalArgumentException("찾을 수 없는 좋아요 입니다.")
//        );
//    }
//
//    @Transactional
//    public Post postCheck(Long postId) {
//        return postRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다.")
//        );
//    }
//
//    @Transactional
//    public Comment commentCheck(Long commentId) {
//        return commentRepository.findById(commentId).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
//        );
//    }
//
//    @Transactional
//    public PostLikes postLikesCheck(String username, Long postId) {
//        return postLikesRepository.findByUsernameAndPostId(username, postId).orElseThrow(
//                () -> new IllegalArgumentException("찾을 수 없는 좋아요 입니다.")
//        );
//    }
}





