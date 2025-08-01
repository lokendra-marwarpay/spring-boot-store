package com.codewithmosh.store.services;

import java.util.Optional;

import com.codewithmosh.store.dtos.PaymentResultDto;
import com.codewithmosh.store.dtos.WebhookRequest;
import com.codewithmosh.store.entities.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);

    Optional<PaymentResultDto> parseWebhookRequest(WebhookRequest request);
}
