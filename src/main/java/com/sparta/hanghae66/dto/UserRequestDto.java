package com.sparta.hanghae66.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @Size(min = 4, max = 10, message = "id는 4 이상, 10 이하만 가능합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "소문자와 숫자만 입력 가능합니다.")
    @NotNull(message = "id를 입력해주세요")
    private String userId;

    @Size(min = 1, max = 10, message = "닉네임은 크기가 최소 1 이상, 10 이하만 가능합니다.")
    @NotNull(message = "닉네임을 입력해주세요")
    private String userName;

    @Size(min = 8, max = 15, message = "password는 8 이상, 15 이하만 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z\\p{Punct}0-9]*$", message = "password는 알파벳 대소문자, 특수문자, 숫자만 가능합니다.")
    @NotNull(message = "password를 입력해주세요")
    private String userPassword;

    private String userRole;
    private String adminToken = "";

    private String userSkill;
    private Long userYear;
}
