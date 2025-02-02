package com.KoreaItAcdemy.OurNotion.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드를 받는 생성자 추가
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String kakaoId;

    public User(String kakaoId) {
        this.kakaoId = kakaoId;
    }
}
