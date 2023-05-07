package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;


@Getter
@Setter
@Entity(name = "TB_COMMENT")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmtId;

    @Column(nullable = false)
    private String cmtContent;

    @ColumnDefault("0")
    private Long cmtLikes;

    @Column(nullable = false)
    private String cmtUserId;

    @Column(nullable = false)
    private String cmtUserName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(String content, String userName) {
        this.cmtContent = content;
        this.cmtUserName = userName;
     }

    public void update(String content) {
        this.cmtContent = content;
    }
//
//    public void commentCountLikes(long likes) {
//        this.commentLikes = likes;
//    }
}
