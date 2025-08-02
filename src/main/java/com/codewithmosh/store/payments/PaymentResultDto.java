package com.codewithmosh.store.payments;

import com.codewithmosh.store.entities.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResultDto {
    private Long orderId;
    private PaymentStatus paymentStatus;
}
