package com.sparta.hanghae66.controller;

import com.sparta.hanghae66.dto.MyPageResponseDto;
import com.sparta.hanghae66.security.UserDetailsImpl;
import com.sparta.hanghae66.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/mypage/{userId}")
    public MyPageResponseDto mypage(@PathVariable String userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myPageService.mypage(userId);
    }
}
