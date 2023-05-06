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
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long postLikesCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long viewCount;


    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setPost(this);
    }

    public Post(PostRequestDto postRequestDto, String username){
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.username = username;

    }

    public void viewCountUp(Long id){
        this.viewCount += 1;
    }
    public void update(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }

}
