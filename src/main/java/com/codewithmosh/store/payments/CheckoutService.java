package com.codewithmosh.store.payments;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codewithmosh.store.auth.AuthService;
import com.codewithmosh.store.carts.CartEmptyException;
import com.codewithmosh.store.carts.CartNotFoundException;
import com.codewithmosh.store.carts.CartRepository;
import com.codewithmosh.store.carts.CartService;
import com.codewithmosh.store.entities.Order;
import com.codewithmosh.store.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // this will include only those field declared with final keyword will
                         // initialize means it'll ignore websiteUrl
@Service
public class CheckoutService {
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try {
            var session = paymentGateway.createCheckoutSession(order);

            cartService.clearCart(cart.getId());

            return new CheckoutResponse(order.getId(), session.getChackoutUrl());
        } catch (PaymentException e) {
            orderRepository.delete(order);
            throw e;
        }
    }

    public void handleWebhookEvent(WebhookRequest request) {
        paymentGateway
                .parseWebhookRequest(request)
                .ifPresent(paymentResult -> {
                    System.out.println("paymentResult" + paymentResult);
                    var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                    order.setStatus(paymentResult.getPaymentStatus());
                    orderRepository.save(order);
                });

    }
}
