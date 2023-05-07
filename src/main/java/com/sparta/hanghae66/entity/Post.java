package com.sparta.hanghae66.entity;

import com.sparta.hanghae66.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name ="TB_POST")
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    @Column(nullable = false)
    private String postSkill;

    @Column
    private String postFile;

    @Column
    @ColumnDefault("0")
    private Long postLikes;

    @Column(nullable = false)
    private String postUserId;

    @Column(nullable = false)
    private String postUserName;

    @Column
    @ColumnDefault("0")
    private Long postVisitCnt;

    @Column
    @ColumnDefault("0")
    private Long cmtCount;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setPost(this);
    }

    public Post(PostRequestDto postRequestDto, String username){
        this.postTitle = postRequestDto.getTitle();
        this.postContent = postRequestDto.getContents();
        this.postUserName= username;
    }

    public void viewCountUp(Long id){
        this.postVisitCnt += 1;
    }

    public void update(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }

    public void postCountLikes(long likes) {
        this.postLikesCount = likes;
    }

}
