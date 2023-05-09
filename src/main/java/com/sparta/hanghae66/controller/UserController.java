package com.sparta.hanghae66.controller;

import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.dto.UserRequestDto;
import com.sparta.hanghae66.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


@Tag(name = "UserController", description = "유저 관련 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 가입 API" , description = "새로운 유저 가입")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "회원 가입 완료" )})
    @PostMapping("/signup")
    public ResponseDto signup(@Valid @RequestBody UserRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for(FieldError fieldError: bindingResult.getFieldErrors()) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseDto(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        return userService.signup(requestDto);
    }

    @Operation(summary = "유저 로그인 API" , description = "로그인, RefreshToken, AccessToken")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "로그인 성공!" )})
    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserRequestDto requestDto, jakarta.servlet.http.HttpServletResponse response) {
        return userService.login(requestDto, response);
    }


    @Operation(summary = "유저 아이디 중복 여부" , description = "중복 여부 확인")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "아이디 중복여부 확인" )})
    @GetMapping("/userCheck/{userId}")
    public ResponseDto userCheck(@Valid @PathVariable String userId) {
        return userService.userCheck(userId);
    }

    @Operation(summary = "유저 로그아웃 API" , description = "로그아웃, 토큰 삭제")
    @ApiResponses(value ={@ApiResponse(responseCode= "200", description = "로그아웃 성공!" )})
    @DeleteMapping("/logout")
    public ResponseDto logOut(@RequestBody UserRequestDto requestDto) {
        return userService.logOut(requestDto);
    }
}
