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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PostController", description = "글 관련 Controller")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final LikeService likeService;

    @Operation(summary = "글 목록 보기 API" , description = "포스트 목록 보여줌")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = " 포스트리스트 보여줌" )})
    @GetMapping("/post")
    public List<PostDto> viewPostList(){
        return postService.viewPostList();
    }

    @Operation(summary = "글 상세 보기 API" , description = "게시글 상세 내용 보여줌 ")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "글 상세 정보 조회" )})
    @GetMapping("/post/{postId}")
    public PostDto viewPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails, jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) {
        return postService.viewPost(postId, userDetails.getUser(), request, response);
    }

    @Operation(summary = "게시글 작성 API" , description = "새로운 게시글 생성")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "게시글 생성" )})
    @PostMapping("/post")
    public PostResponseDto createPost(@Valid @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for(FieldError fieldError: bindingResult.getFieldErrors()) {
                sb.append(fieldError.getDefaultMessage());
            }
            return null;
        }
        else
            return postService.createPost(postRequestDto, userDetails.getUser());
    }

    @Operation(summary = "게시글 수정 API" , description = "이미 존재하는 게시글 수정")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "글 수정 완료 메세지 리턴 해줌" )})
    @PutMapping("/post/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(postId, postRequestDto, userDetails.getUser());
    }

    @Operation(summary = "게시글 삭제 API" , description = "이미 존재하는 게시글 삭제")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "글 삭제 완료 메세지 리턴" )})
    @DeleteMapping("/post/{postId}")
    public ResponseDto deletePost(@PathVariable Long postId,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(postId, userDetails.getUser());
    }

    @Operation(summary = "게시글 좋아요 API" , description = "게시글 좋아요")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "게시글 좋아요, 한 번더 누르면 true, false" )})
    @PostMapping("/like/{postId}")
    public ResponseDto postLikeService(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetailsImplement) {
        return likeService.postLikeService(postId, userDetailsImplement.getUser());
    }

    @Operation(summary = "게시글 검색 API" , description = "키워드에 따른 검색")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "게시글 검색" )})
    @GetMapping("/post/search")
    public List<PostDto> searchPost(@RequestParam(value = "keyword") String keyword
                                    ,@RequestParam("sortBy") String sortBy

    ){
        return postService.searchPost(keyword, sortBy);
    }
}
