package com.tradex.Repositary;

import com.tradex.modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepositary extends JpaRepository<VerificationCode,Long> {
    VerificationCode findByUserId(Long userId);
}
