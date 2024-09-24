package com.tradex.Repositary;

import com.tradex.modal.Wallet;
import com.tradex.modal.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface WalletTransactionRepositary extends JpaRepository<WalletTransaction,Long> {

    List<WalletTransaction> findByWalletOrderByDateDesc(Wallet wallet);

}

