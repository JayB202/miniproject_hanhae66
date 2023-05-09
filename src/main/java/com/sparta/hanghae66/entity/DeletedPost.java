package com.sparta.hanghae66.entity;


import com.sparta.hanghae66.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;



@Getter
@Setter
@Entity(name ="TB_DELPOST")
@NoArgsConstructor
public class DeletedPost extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long delPostId;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    @Column(nullable = false)
    private String postSkill;

    @Column
    private String postFile;

    @Column
    private String userSkill;

    @Column
    private Long userYear;

    @Column
    @ColumnDefault("0")
    private Long postLikes;

    @Column(nullable = false)
    private String postUserId;

    @Column(nullable = false)
    private String postUserName;



    public DeletedPost(Post post){

        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postUserName= post.getPostUserName();
        this.postUserId = post.getPostUserId();
        this.postSkill = post.getPostSkill();
        this.userSkill = post.getUserSkill();
        this.userYear = post.getUserYear();


    }
}
