package com.example.bbmarketb.repository;

import com.example.bbmarketb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    //기능선언
    boolean existsByUserId (String id);
    Optional<User> findByUserId(String id);
    Optional<User> findBySocialId(String socialId);
}
