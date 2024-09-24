package com.tradex.Repositary;



import com.tradex.modal.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepositary extends JpaRepository<OrderItem,Long> {
}
