package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name="TB_USER")
@NoArgsConstructor
public class User extends UserDate {
    @Id
    @Column(name = "userId", nullable = false, unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private Long userYear;

    @Column(nullable = false)
    private String userSkill;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;


    public User(String id, String userName, String userPassword, Long userYear, String userSkill, UserRole role) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userYear = userYear;
        this.userSkill = userSkill;
        this.role = role;
    }

}
