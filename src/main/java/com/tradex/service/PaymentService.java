package com.tradex.service;

import com.tradex.domain.PaymentMethod;
import com.tradex.modal.PaymentOrder;
import com.tradex.modal.User;
import com.tradex.response.PaymentResponse;

public interface PaymentService {

    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean ProccedPaymentOrder (PaymentOrder paymentOrder,
                                 String paymentId) throws Exception;

    PaymentResponse createRazorpayPaymentLink(User user,
                                              Long Amount,
                                              Long orderId) throws Exception;

    PaymentResponse createStripePaymentLink(User user, Long Amount,
                                            Long orderId) throws Exception;
}
