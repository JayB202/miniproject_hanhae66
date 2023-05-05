package com.sparta.hanghae66.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private Long postLikesCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Long id, String title, String contents, Long postLikesCount) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.postLikesCount = postLikesCount;
    }

    public void setCreatedTime(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setModifiedTime(LocalDateTime modifiedAt){
        this.modifiedAt = modifiedAt;
    }
}
