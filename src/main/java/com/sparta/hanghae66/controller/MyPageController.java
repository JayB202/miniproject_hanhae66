package com.sparta.hanghae66.controller;

import com.sparta.hanghae66.dto.MyPageResponseDto;
import com.sparta.hanghae66.security.UserDetailsImpl;
import com.sparta.hanghae66.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MyPageController", description = "내 화면 Controller")
@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    @Operation(summary = "마이페이지 API" , description = "마이페이지에 필요한 정보 리스폰스")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "유저정보, 포스트리스트, 댓글 리스트 보냄" )})
    @GetMapping("/mypage/{userId}")
    public MyPageResponseDto mypage(@PathVariable String userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myPageService.mypage(userId);
    }
}
