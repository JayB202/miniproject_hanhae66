package com.sparta.hanghae66.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor   //    <---- 필드가 있으면 자동으로 모두 매개변수로 생성자 만드는 아이
public class ResponseDto {
    private String msg;   //<---- 메세지
    private HttpStatus code;   // < ---- 상태코드

}