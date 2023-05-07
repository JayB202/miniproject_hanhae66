package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmtId;

    @Column(nullable = false)
    private String cmtContent;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private Long commentLikes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(String content, String userName) {
        this.cmtContent = content;
        this.cmtUserName = userName;
    }

    public void update(String content) {
        this.content = content;
    }

    public void commentCountLikes(long likes) {
        this.commentLikes = likes;
    }
}
