package com.tradex.service;

import com.tradex.domain.VerificationType;
import com.tradex.modal.User;


public interface UserService {

    public User findUserProfileByJwt(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public User findUserById(Long userId) throws Exception;

    public User verifyUser(User user) throws Exception;

    public User enabledTwoFactorAuthentication(VerificationType verificationType,
                                               String sendTo, User user) throws Exception;

//	public List<User> getPenddingRestaurantOwner();

    User updatePassword(User user, String newPassword);

    void sendUpdatePasswordOtp(String email,String otp);

//	void sendPasswordResetEmail(User user);
}
