package com.sparta.hanghae66.dto;

import com.sparta.hanghae66.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MyPageResponseDto {
    private String userId;
    private String userName;
    private String userSkill;
    private Long userYear;
    private LocalDate userCrdat;
    private List<PostDto> postList;
    private List<CommentDto> commentList;


    public MyPageResponseDto(User user){
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.userSkill = user.getUserSkill();
        this.userYear = user.getUserYear();
        this.userCrdat = user.getCreatedAt();
    }


}
