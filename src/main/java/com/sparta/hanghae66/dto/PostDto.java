package com.sparta.hanghae66.dto;

import com.sparta.hanghae66.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long id;
    private String title;
    private String contents;
    private Long postLikesCount;
    private List<CommentDto> commentList;


    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.postLikesCount = post.getPostLikesCount();
    }

}
