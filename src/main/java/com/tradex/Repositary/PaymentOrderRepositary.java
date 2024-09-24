package com.tradex.Repositary;

import com.tradex.modal.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepositary extends JpaRepository<PaymentOrder,Long> {
}
