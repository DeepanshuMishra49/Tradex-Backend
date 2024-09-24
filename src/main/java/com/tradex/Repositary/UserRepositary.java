package com.tradex.Repositary;

import com.tradex.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
