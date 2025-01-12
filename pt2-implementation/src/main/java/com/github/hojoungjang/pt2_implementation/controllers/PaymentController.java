package com.github.hojoungjang.pt2_implementation.controllers;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.hojoungjang.pt2_implementation.model.Payment;
import com.github.hojoungjang.pt2_implementation.proxy.PaymentsProxy;
import com.github.hojoungjang.pt2_implementation.proxy.PaymentsProxyRestTemplate;

@RestController
public class PaymentController {
    private final PaymentsProxy paymentsProxy;
    private final PaymentsProxyRestTemplate paymentsProxyRestTemplate;
    private static Logger logger = Logger.getLogger(PaymentController.class.getName());

    public PaymentController(
        PaymentsProxy paymentsProxy,
        PaymentsProxyRestTemplate paymentsProxyRestTemplate
    ) {
        this.paymentsProxy = paymentsProxy;
        this.paymentsProxyRestTemplate = paymentsProxyRestTemplate;
    }

    @PostMapping("/payment")
    public Payment makePayment(
        @RequestBody Payment payment
    ) {
        logger.info("received payment; amount: " + payment.getAmount());
        String requestId = UUID.randomUUID().toString();
        return paymentsProxy.createPayment(requestId, payment);
    }

    @PostMapping("/payment-rest-template")
    public Payment makePaymentRestTemplate(
        @RequestBody Payment payment
    ) {
        logger.info("received payment; amount: " + payment.getAmount());
        return paymentsProxyRestTemplate.createPayment(payment);
    }
}
