package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name="USERS")
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    private String Id;

    private String username;

    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;


    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
