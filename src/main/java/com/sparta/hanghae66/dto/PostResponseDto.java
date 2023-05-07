package com.sparta.hanghae66.dto;

import com.sparta.hanghae66.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;

    private String postTitle;

    private String postContent;

    private String postSkill;

    private String postFile;

    private Long postLikes;

    private String postUserId;

    private String postUserName;

    private Long postVisitCnt;

    private Long cmtCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Long postId, String postTitle, String postContent, String postSkill, String postFile, Long postLikes, String postUserId, String postUserName, Long postVisitCnt, Long cmtCount) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postSkill = postSkill;
        this.postFile = postFile;
        this.postLikes = postLikes;
        this.postUserId = postUserId;
        this.postUserName = postUserName;
        this.postVisitCnt = postVisitCnt;
        this.cmtCount = cmtCount;
    }

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postSkill = post.getPostSkill();
        this.postFile = post.getPostFile();
        this.postLikes = post.getPostLikes();
        this.postUserId = post.getPostUserId();
        this.postUserName = post.getPostUserName();
        this.postVisitCnt = post.getPostVisitCnt();
        this.cmtCount = post.getCmtCount();
    }

    public void setCreatedTime(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setModifiedTime(LocalDateTime modifiedAt){
        this.modifiedAt = modifiedAt;
    }
}
