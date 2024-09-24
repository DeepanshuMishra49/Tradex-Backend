package com.tradex.service;

import com.tradex.domain.VerificationType;
import com.tradex.modal.User;
import com.tradex.modal.VerificationCode;

public interface VerificationCodeService {

    VerificationCode sendVerificationCode(User user, VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode  getVerificationCodeByUser(Long userId);

    Boolean VerifyOtp(String opt, VerificationCode verificationCode);

    void deleteVerificationCodeById(VerificationCode verificationCode);


}
