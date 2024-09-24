package com.tradex.Repositary;

import com.tradex.modal.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepositary extends JpaRepository<Watchlist,Long> {

    Watchlist findByUserId(Long userId);

}
