package com.sparta.hanghae66.controller;

import com.sparta.hanghae66.dto.CommentRequestDto;
import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.security.UserDetailsImpl;
import com.sparta.hanghae66.service.CommentService;
import com.sparta.hanghae66.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@Tag(name = "CommentController", description = "댓글 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;
    private final LikeService likeService;

    @Operation(summary = "댓글 작성 API" , description = "댓글 생성")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "댓글 작성 완료" )})
    @PostMapping("{postId}")
    public ResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto.getCmtContent(), userDetails.getUser());

    }

    @Operation(summary = "댓글 수정 API" , description = "댓글 수정")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "댓글 수정 완료" )})
    @PutMapping("{commentId}")
    public ResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
    }

    @Operation(summary = "댓글 삭제 API" , description = "댓글 삭제")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "댓글 삭제 완료" )})
    @DeleteMapping("{commentId}")
    public ResponseDto  deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getUser());
    }

    @Operation(summary = "댓글 좋아요 API" , description = "댓글 좋아요 생성")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "댓글 좋아요" )})
    @PostMapping("like/{commentId}")
    public ResponseDto commentLikeService(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.commentLikeService(commentId, userDetails.getUser());
    }

}
