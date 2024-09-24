package com.tradex.service;

import com.tradex.Repositary.UserRepositary;
import com.tradex.config.JwtProvider;
import com.tradex.domain.VerificationType;
import com.tradex.modal.TwoFactorAuth;
import com.tradex.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepositary userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailFromJwtToken(jwt);


        User user = userRepository.findByEmail(email);

        if(user==null) {
            throw new Exception("user not exist with email "+email);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String username) throws Exception {

        User user=userRepository.findByEmail(username);

        if(user!=null) {

            return user;
        }

        throw new Exception("user not exist with username "+username);
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);

        if(opt.isEmpty()) {
            throw new Exception("user not found with id "+userId);
        }
        return opt.get();
    }

    @Override
    public User verifyUser(User user) throws Exception {
        user.setVerified(true);
        return userRepository.save(user);
    }

    @Override
    public User enabledTwoFactorAuthentication(
            VerificationType verificationType, String sendTo,User user) throws Exception {
        TwoFactorAuth twoFactorAuth=new TwoFactorAuth();
        twoFactorAuth.setEnabled(true);
        twoFactorAuth.setSendTo(verificationType);

        user.setTwoFactorAuth(twoFactorAuth);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @Override
    public void sendUpdatePasswordOtp(String email, String otp) {

    }

}
