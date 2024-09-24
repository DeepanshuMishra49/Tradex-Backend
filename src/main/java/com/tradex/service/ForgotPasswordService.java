package com.tradex.service;

import com.tradex.domain.VerificationType;
import com.tradex.modal.ForgotPasswordToken;
import com.tradex.modal.User;
import org.springframework.stereotype.Service;

@Service
public interface ForgotPasswordService {

    ForgotPasswordToken createToken(User user, String id, String otp,
                                    VerificationType verificationType, String sendTo);

    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(Long userId);

    void deleteToken(ForgotPasswordToken token);

    boolean verifyToken(ForgotPasswordToken token,String otp);
}