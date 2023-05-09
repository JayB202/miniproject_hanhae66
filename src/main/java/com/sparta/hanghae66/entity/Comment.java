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

    @Column(nullable = false)
    private Long cmtUserYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(String content, String userName, String userId, Long cmtUserYear, Post post) {
        this.cmtContent = content;
        this.cmtUserName = userName;
        this.cmtUserId = userId;
        this.cmtUserYear = cmtUserYear;
        this.post = post;
     }

    public void update(String content) {
        this.cmtContent = content;
    }
}
