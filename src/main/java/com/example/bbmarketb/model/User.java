package com.example.bbmarketb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name="users")
//model - 테이블과 관계성을 가지기 위한 구조 (테이블 자체)
//1 model:1 controller
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    private String id;
    private String password;
    private String name;
    private String phone_number;
    private String adress;
    private String role;
    private String status;
    private LocalDateTime create_at;
    private LocalDateTime latest_at;
    private String social_id;
}
