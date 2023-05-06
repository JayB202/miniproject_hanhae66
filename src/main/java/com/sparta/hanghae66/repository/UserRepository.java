package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
