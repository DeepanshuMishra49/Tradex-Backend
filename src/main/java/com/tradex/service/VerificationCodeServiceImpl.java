package com.tradex.service;

import com.tradex.Repositary.VerificationCodeRepositary;
import com.tradex.domain.VerificationType;
import com.tradex.modal.User;
import com.tradex.modal.VerificationCode;
import com.tradex.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService{

    @Autowired
    private VerificationCodeRepositary verificationRepositary;


    @Override
    public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {
        VerificationCode verificationCode1 = new VerificationCode();

        verificationCode1.setOtp(OtpUtils.generateOTP());
        verificationCode1.setVerificationType(verificationType);
        verificationCode1.setUser(user);
        return verificationRepositary.save(verificationCode1);
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) throws Exception {
        Optional<VerificationCode> verificationCodeOption=verificationRepositary.findById(id);
        if(verificationCodeOption.isEmpty()){
            throw new Exception("verification not found");
        }
        return verificationCodeOption.get();
    }

    @Override
    public VerificationCode getVerificationCodeByUser (Long userId) {
        return verificationRepositary.findByUserId(userId);
    }

    @Override
    public Boolean VerifyOtp(String opt, VerificationCode verificationCode) {
        return null;
    }

    @Override
    public void deleteVerificationCodeById(VerificationCode verificationCode) {
        verificationRepositary.delete(verificationCode);
    }

}
