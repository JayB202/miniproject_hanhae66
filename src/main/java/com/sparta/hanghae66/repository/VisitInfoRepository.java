package com.sparta.hanghae66.repository;

import com.sparta.hanghae66.entity.VisitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitInfoRepository extends JpaRepository<VisitInfo, Long> {

    @Query("SELECT v FROM TB_VISITINFO v WHERE v.visitUserId = :userId")
    Optional<VisitInfo> findVisitInfoByVisitUserId_Opt(@Param("userId") String userId);
    //Optional<VisitInfo> findVisitInfoByVisitUserId(String userId);

    @Query("SELECT v FROM TB_VISITINFO v WHERE v.visitUserId = :userId")
    VisitInfo findVisitInfoByVisitUserId_ent(@Param("userId") String userId);
}
