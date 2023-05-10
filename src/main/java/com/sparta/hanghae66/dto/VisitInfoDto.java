package com.sparta.hanghae66.dto;

import com.sparta.hanghae66.entity.VisitInfo;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VisitInfoDto {

    private Long vist_id;

    private String visitUserId;

    private String visitData;

    private int visitCrdAt;

    private int visitCrTime;

    public VisitInfoDto(VisitInfo visitInfo) {
        this.vist_id = visitInfo.getId();
        this.visitUserId = visitInfo.getVisitUserId();
        this.visitData = visitInfo.getVisitData();
        this.visitCrdAt = visitInfo.getVisitCrdAt();
        this.visitCrTime = visitInfo.getVisitCrTime();
    }
}
