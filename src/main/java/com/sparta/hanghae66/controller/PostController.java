package com.sparta.hanghae66.controller;

import com.sparta.hanghae66.dto.PostDto;
import com.sparta.hanghae66.dto.PostRequestDto;
import com.sparta.hanghae66.dto.PostResponseDto;
import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.security.UserDetailsImpl;
import com.sparta.hanghae66.service.LikeService;
import com.sparta.hanghae66.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "PostController", description = "게시글 Controller")
public class PostController {
    private final PostService postService;
    private final LikeService likeService;

    @Operation(summary = "게시글 목록 조회 API", description = "게시글 목록을 조회")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 저장 완료")})
    @GetMapping("/")
    public List<PostDto> viewPostList(){
        return postService.viewPostList();
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글을 조회")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 저장 완료"),
            @ApiResponse(responseCode = "403", description = "로그인이 필요 합니다.")})
    @GetMapping("/{postId}")
    public PostDto viewPost(@PathVariable Long postId){
        return postService.viewPost(postId);
    }

    @Operation(summary = "게시글 생성 API", description = "게시글을 생성")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 저장 완료"),
            @ApiResponse(responseCode = "403", description = "로그인이 필요 합니다.")})
    @PostMapping("/")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImplement){
        return postService.createPost(postRequestDto, userDetailsImplement.getUser());
    }

    @Operation(summary = "게시글 생성 API", description = "게시글을 생성")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 저장 완료"),
            @ApiResponse(responseCode = "403", description = "로그인이 필요 합니다.")})
    @PutMapping("/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImplement){
        return postService.updatePost(postId, postRequestDto, userDetailsImplement.getUser());
    }


    @Operation(summary = "게시글 생성 API", description = "게시글을 생성")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 저장 완료"),
            @ApiResponse(responseCode = "403", description = "로그인이 필요 합니다.")})
    @DeleteMapping("/{postId}")
    public ResponseDto deletePost(@PathVariable Long postId,  @AuthenticationPrincipal UserDetailsImpl userDetailsImplement){
        return postService.deletePost(postId, userDetailsImplement.getUser());

    }

    @PostMapping("/like/{postId}")
    public ResponseDto postLikeService(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetailsImplement) {
        return likeService.postLikeService(postId, userDetailsImplement.getUser());
    }
}
