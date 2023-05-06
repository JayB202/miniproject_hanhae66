package com.sparta.hanghae66.controller;

import com.sparta.hanghae66.dto.CommentRequestDto;
import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.security.UserDetailsImpl;
import com.sparta.hanghae66.service.CommentService;
import com.sparta.hanghae66.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;
    private final LikeService likeService;

    @PostMapping("{postId}")
    public ResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto.getComment(), String.valueOf(userDetails.getUser().getId()));
    }

    @PutMapping("{commentId}")
    public ResponseDto updateComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId, userDetails.getUser());
    }

    @DeleteMapping("{commentId}")
    public ResponseDto  deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getUser());
    }

    @PostMapping("like/{commentId}")
    public ResponseDto commentLikeService(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetailsImplement) {
        return likeService.commentLikeService(commentId, userDetailsImplement.getUser());
    }

}
