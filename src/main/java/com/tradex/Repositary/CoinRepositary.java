package com.tradex.Repositary;

import com.tradex.modal.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepositary extends JpaRepository<Coin,String> {
}

