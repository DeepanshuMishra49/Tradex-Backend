package com.tradex.Controller;

import com.tradex.Repositary.UserRepositary;
import com.tradex.config.JwtProvider;
import com.tradex.modal.TwoFactorOTP;
import com.tradex.modal.User;
import com.tradex.response.AuthResponse;
import com.tradex.service.*;
import com.tradex.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepositary userRepositary;

    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;

    @Autowired
    private OtpUtils otpUtils;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private WatchlistService watchlistService;

    //this is for creating the userdetails like username and password
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {
        // Check if email is already in use
        User isEmailExist = userRepositary.findByEmail(user.getEmail());
        if (isEmailExist != null) {
            throw new Exception("Email is already used with another account");
        }

        // Create a new user entity
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFullName(user.getFullName());

        // Save the new user to the repository
        User savedUser = userRepositary.save(newUser);


        watchlistService.createWatchList(savedUser);
        // Manually authenticate the user
        Authentication auth = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),
                savedUser.getPassword()// Empty authorities list, adjust as needed
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Generate JWT token
        String jwt = JwtProvider.generateToken(auth);

        // Prepare the response
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("Registration successful");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    //this is login by adding username and password
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {

        String userName = user.getEmail();
        String password =  user.getPassword();




        // Manually authenticate the user
        Authentication auth =authenticate(userName,password);

        User authUser=userRepositary.findByEmail(userName);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Generate JWT token
        String jwt = JwtProvider.generateToken(auth);

        if(user.getTwoFactorAuth().isEnabled()) {
            AuthResponse res=new AuthResponse();
            res.setMessage("Two factor auth is enabled");
            res.setTwoFactorAuthEnabled(true);
            String otp=OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP=twoFactorOtpService.findByUser(authUser.getId());
            if (oldTwoFactorOTP!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
            }
            TwoFactorOTP newTwoFactor0TP=twoFactorOtpService.createTwoFactorOtp(authUser, otp,jwt);

            emailService.sendVerificationOtpEmail(userName, otp);
            res.setSession(new TwoFactorOTP().getId());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }

        // Prepare the response
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login successful");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(userName);
        if(userDetails == null){
            throw new BadCredentialsException("invalid username");
        }

        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }

    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySigningOtp(
            @PathVariable String otp,
            @RequestParam String id
    ) throws Exception {


        TwoFactorOTP twoFactorOTP = twoFactorOtpService.findById(id);

        if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)){
            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("Two factor authentication verified");
            authResponse.setTwoFactorAuthEnabled(true);
            authResponse.setJwt(twoFactorOTP.getJwt());
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
        throw new Exception("invalid otp");
    }

}
