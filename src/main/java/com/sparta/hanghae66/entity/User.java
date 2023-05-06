package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name="USERS")
@Table(name="USERS")
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private int userSkill;

    @Column(nullable = false)
    private int userYear;

    public User(String username, String password, UserRole role, int userSkill, int userYear) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userSkill = userSkill;
        this.userYear = userYear;

    }

}
