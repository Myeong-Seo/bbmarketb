package com.example.bbmarketb.repository;

import com.example.bbmarketb.model.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetTokenRepository extends JpaRepository<ResetToken, String> {
    Optional<ResetToken> findByToken(String token);

}
