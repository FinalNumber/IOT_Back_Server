package com.example.iot_project_backserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "bodytemp") // 사용할 테이블 이름 작성
@Getter
@Setter
public class BodyTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성
    private Long id; //TODO 사용자 ID를 기본키로 사용하려면 수정 필요

    private Long userId;// 사용자 ID
    @ElementCollection // 리스트 형태로 측정 데이터를 저장
    private List<Float> bodydata; // 측정 데이터
}





