package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity(name = "TB_POSTLIKES")
@NoArgsConstructor
public class PostLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikesNum;

    @Column(nullable = false)
    private Long postLikesId;

    @Column(nullable = false)
    private String postLikesUserName;

    @Column(nullable = false)
    private String postLikesUserId;

    @ColumnDefault("false")
    private boolean postLikes;


    public PostLikes(Long postLikesId, String postLikesUserName, String postLikesUserId, boolean postLikes) {
        this.postLikesId = postLikesId;
        this.postLikesUserName = postLikesUserName;
        this.postLikesUserId = postLikesUserId;
        this.postLikes = postLikes;
    }

}
