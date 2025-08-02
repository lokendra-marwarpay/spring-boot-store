package com.codewithmosh.store.payments;

import java.util.Optional;

import com.codewithmosh.store.entities.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);

    Optional<PaymentResultDto> parseWebhookRequest(WebhookRequest request);
}
