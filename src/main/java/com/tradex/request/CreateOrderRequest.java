package com.tradex.request;

import com.tradex.domain.OrderType;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;
}

