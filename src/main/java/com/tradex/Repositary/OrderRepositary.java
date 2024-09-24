package com.tradex.Repositary;

import com.tradex.modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositary extends JpaRepository<Order,Long> {

    public List<Order>findByUserId(Long userId);
}

