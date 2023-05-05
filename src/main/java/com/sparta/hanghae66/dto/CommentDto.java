package com.sparta.hanghae66.dto;

import com.sparta.hanghae66.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long commentId;
    private String userId;
    private String content;
    private Long commentLikes;

    public CommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.userId = comment.getUserId();
        this.content = comment.getContent();
        this.commentLikes = comment.getCommentLikes();
    }
}
