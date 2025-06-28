package com.example.bbmarketb.repository;

import com.example.bbmarketb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 기능 선언
    boolean existsById(String id);
    String findById(String id);


}
