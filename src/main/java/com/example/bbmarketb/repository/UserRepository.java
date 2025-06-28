package com.example.bbmarketb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bbmarketb.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    //기능선언
    boolean existsById (String id);
    String findById(String id);
    //String findBy()
}
