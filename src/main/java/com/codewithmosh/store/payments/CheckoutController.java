package com.codewithmosh.store.payments;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithmosh.store.carts.CartEmptyException;
import com.codewithmosh.store.carts.CartNotFoundException;
import com.codewithmosh.store.common.ErrorDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(
            @Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestHeader("Stripe-Signature") String signature,
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload) {
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorDto> handlePaymentException() {
        return ResponseEntity.internalServerError().body(new ErrorDto("Error creating checkout session"));
    }

    @ExceptionHandler({ CartNotFoundException.class, CartEmptyException.class })
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }
}
