package com.github.hojoungjang.pt2_implementation.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.hojoungjang.pt2_implementation.model.PaymentDetails;
import com.github.hojoungjang.pt2_implementation.service.PaymentService;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    private static Logger logger = Logger.getLogger(PaymentController.class.getName());

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentDetails> makePayment(
        @RequestBody PaymentDetails paymentDetails
    ) {
        logger.info("Received payment " + paymentDetails.getAmount());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentDetails);
    }
}
