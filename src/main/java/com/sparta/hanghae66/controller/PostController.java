package com.sparta.hanghae66.controller;

import com.sparta.hanghae66.dto.PostDto;
import com.sparta.hanghae66.dto.PostRequestDto;
import com.sparta.hanghae66.dto.PostResponseDto;
import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.security.UserDetailsImpl;
import com.sparta.hanghae66.service.LikeService;
import com.sparta.hanghae66.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final LikeService likeService;

    @GetMapping("/post")
    public List<PostDto> viewPostList(){
        return postService.viewPostList();
    }

    @GetMapping("/post/{postId}")
    public PostDto viewPost(@PathVariable Long postId){
        return postService.viewPost(postId);
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("postRequestDto = " + postRequestDto.getTitle());
        System.out.println("userDetails = " + userDetails.getUser().getUserName());
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    @PutMapping("/post/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(postId, postRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/post/{postId}")
    public ResponseDto deletePost(@PathVariable Long postId,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(postId, userDetails.getUser());

    }

    @PostMapping("/like/{postId}")
    public ResponseDto postLikeService(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetailsImplement) {
        return likeService.postLikeService(postId, userDetailsImplement.getUser());
    }
}
