package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Query("SELECT r.userId FROM RefreshToken r WHERE r.userId = :userId")
    Optional<RefreshToken> findByUserId(@Param("userId") String userId);
}
