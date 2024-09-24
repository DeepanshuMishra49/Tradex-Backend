package com.tradex.Controller;

import com.tradex.service.EmailService;
import com.tradex.service.UserService;
import com.tradex.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {
    private final VerificationCodeService verificationcodeService;
    private final UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    public VerificationController(VerificationCodeService verificationcodeService, UserService userService) {
        this.verificationcodeService = verificationcodeService;
        this.userService = userService;
    }




}
