package com.example.iot_project_backserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bodydata") // 사용할 테이블 이름 작성
@Getter
@Setter
public class BodyTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성
    private Long id; //TODO 사용자 ID를 기본키로 사용하려면 수정 필요

    private String userId;// 사용자 ID

    //@ElementCollection // 리스트 형태로 측정 데이터를 저장
    private float bodydata; // 측정 데이터*/

    @Column(nullable = true)
    private String pandan; //판단 데이터

    private String device_id;

    //데이터 저장 시간 기록
    @Column(name = "created_at", updatable = false) // created_at 컬럼 추가
    private LocalDateTime createdAt;

    @PrePersist // 엔티티가 저장되기 전에 호출
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // 현재 시간으로 설정
    }

}




