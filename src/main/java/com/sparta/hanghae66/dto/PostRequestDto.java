package com.sparta.hanghae66.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    @NotNull(message = "제목을 입력해주세요.")
    @NotBlank(message = "제목을 입력해주세요.")
    @NotEmpty(message = "제목을 입력해주세요.")
    private String postTitle;

    @NotNull(message = "내용을 입력해주세요.")
    @NotBlank(message = "내용을 입력해주세요.")
    @NotEmpty(message = "내용을 입력해주세요.")
    private String postContent;

    @NotNull(message = "주특기를 선택해주세요.")
    @NotBlank(message = "주특기를 선택해주세요.")
    @NotEmpty(message = "주특기를 선택해주세요.")
    private String postSkill;
}
