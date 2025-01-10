package com.github.hojoungjang.pt2_implementation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hojoungjang.pt2_implementation.exception.NotEnoughMoneyException;
import com.github.hojoungjang.pt2_implementation.model.ErrorDetails;
import com.github.hojoungjang.pt2_implementation.model.PaymentDetails;
import com.github.hojoungjang.pt2_implementation.service.PaymentService;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<?> makePayment() {
        try {
            PaymentDetails paymentDetails = paymentService.processPayment();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentDetails);
        } catch (NotEnoughMoneyException e) {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setMessage("Not enough money to make a payment.");
            return ResponseEntity.badRequest().body(errorDetails);
        }
    }
}
