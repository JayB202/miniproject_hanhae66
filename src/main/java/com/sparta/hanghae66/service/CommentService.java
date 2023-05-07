package com.sparta.hanghae66.service;

import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.entity.Comment;
import com.sparta.hanghae66.entity.Post;
import com.sparta.hanghae66.entity.User;
import com.sparta.hanghae66.entity.UserRole;
import com.sparta.hanghae66.repository.CommentLikesRepository;
import com.sparta.hanghae66.repository.CommentRepository;
import com.sparta.hanghae66.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    public ResponseDto createComment(Long postId, String content, User user) {
        Post post = findPost(postId);

        Comment comment = new Comment(content, user.getUserName());
        post.addComment(comment);

        commentRepository.save(comment);

        return new ResponseDto("댓글 저장 완료", HttpStatus.OK);
    }

    @Transactional
    public ResponseDto updateComment(Long commentId, User user) {
        try {
            Comment comment = findComment(commentId);
            UserRole userRole = user.getRole();

            switch (userRole) {
                case USER:
                    if (StringUtils.pathEquals(comment.getCmtUserId(), user.getId())) {
                        commentRepository.delete(comment);
                        return new ResponseDto("삭제완료", HttpStatus.OK);
                    }
                case ADMIN:
                    commentRepository.delete(comment);
                    return new ResponseDto("삭제완료", HttpStatus.OK);
                default:
                    return null;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public ResponseDto deleteComment(Long commentId, User user) {

        try {
            Comment comment = findComment(commentId);
            UserRole userRole = user.getRole();

            switch (userRole) {
                case USER:
                    if (StringUtils.pathEquals(comment.getCmtUserId(), user.getId())) {
                        commentRepository.delete(comment);
                        return new ResponseDto("삭제완료", HttpStatus.OK);
                    }
                case ADMIN:
                    commentRepository.delete(comment);
                    return new ResponseDto("삭제완료", HttpStatus.OK);
                default:
                    return null;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    public List<Comment> myCommentList(User user) {
        List<Comment> commentList = commentRepository.findByCommentUserId(user.getId());
        return commentList;
    }

    @Transactional(readOnly = true)
    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );
    }

    @Transactional(readOnly = true)
    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
    }


}
