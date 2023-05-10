package com.sparta.hanghae66.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TB_VISITINFO")
public class VisitInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String visitUserId;

    @Column(nullable = false)
    private String visitData;

    @Column(nullable = false)
    private int visitCrdAt;

    @Column(nullable = false)
    private int visitCrTime;

    public VisitInfo(String visitUserId, String visitData, int visitCrdAt, int visitCrTime) {
        this.visitUserId = visitUserId;
        this.visitData = visitData;
        this.visitCrdAt = visitCrdAt;
        this.visitCrTime = visitCrTime;
    }
}
