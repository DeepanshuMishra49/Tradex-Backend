package com.tradex.Repositary;

import com.tradex.modal.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepositary extends JpaRepository<Wallet,Long> {

    public Wallet findByUserId(Long userId);


}
