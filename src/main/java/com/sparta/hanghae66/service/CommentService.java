package com.sparta.hanghae66.service;

import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.entity.Post;
import com.sparta.hanghae66.entity.Comment;
import com.sparta.hanghae66.entity.CommentLikes;
import com.sparta.hanghae66.entity.UserRole;
import com.sparta.hanghae66.entity.User;
import com.sparta.hanghae66.repository.CommentLikesRepository;
import com.sparta.hanghae66.repository.CommentRepository;
import com.sparta.hanghae66.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    public ResponseDto createComment(Long postId, String content, String userId) {
        Post post = postCheck(postId);

        Comment comment = new Comment(content, userId);
        post.addComment(comment);

        commentRepository.save(comment);

        return new ResponseDto("댓글 저장 완료", HttpStatus.OK);
    }

    @Transactional
    public ResponseDto  updateComment(Long commentId, User user) {

        Comment comment = commentCheck(commentId);

        if (comment.getUserId().equals(user.getId()) || user.getRole().equals(UserRole.ADMIN)) {
            comment.update(comment.getContent());
            return new ResponseDto("댓글 수정 완료.", HttpStatus.OK);
        } else {
            return new ResponseDto("댓글 수정 실패.", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseDto deleteComment(Long commentId, User user) {

        Comment comment = commentCheck(commentId);

        if (comment.getUserId().equals(user.getId()) || user.getRole().equals(UserRole.ADMIN)) {
            List<CommentLikes> commentLikes = commentLikesRepository.findAll();
            commentLikesRepository.deleteAll(commentLikes);
            commentRepository.delete(comment);
            return new ResponseDto("댓글 삭제 완료.", HttpStatus.OK);
        } else {
            return new ResponseDto("댓글 삭제 실패.", HttpStatus.BAD_REQUEST);
        }
    }

    public Post postCheck(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );
    }

    public Comment commentCheck(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
    }
}
