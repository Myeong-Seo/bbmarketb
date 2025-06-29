package com.example.bbmarketb.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @Column(name = "userid")
    private String userId;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;

    private String role;
    private String status;

    @Column(name = "create_at")
    private LocalDateTime create_at;
    @Column(name = "latest_at")
    private LocalDateTime latest_at;
    @Column(name = "social_id")
    private String social_id;
}
