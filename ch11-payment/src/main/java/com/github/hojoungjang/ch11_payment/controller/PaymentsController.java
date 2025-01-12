package com.github.hojoungjang.ch11_payment.controller;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.github.hojoungjang.ch11_payment.model.Payment;

@RestController
public class PaymentsController {

    private static Logger logger = Logger.getLogger(PaymentsController.class.getName());

    @PostMapping("/payment")
    public ResponseEntity<Payment> makePayment(
        @RequestHeader String requestId,
        @RequestBody Payment payment
    ) {
        logger.info("Received request with ID " + requestId + "; Payment Amount: " + payment.getAmount());
        payment.setId(UUID.randomUUID().toString());
        return ResponseEntity
            .status(HttpStatus.OK)
            .header("requestId", requestId)
            .body(payment);
    }
}