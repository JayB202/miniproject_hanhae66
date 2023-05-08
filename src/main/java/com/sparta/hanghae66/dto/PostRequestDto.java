package com.sparta.hanghae66.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String postTitle;
    private String postContent;

    private String postSkill;
}
