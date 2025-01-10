package com.github.hojoungjang.pt2_implementation.service;

import org.springframework.stereotype.Service;

import com.github.hojoungjang.pt2_implementation.exception.NotEnoughMoneyException;
import com.github.hojoungjang.pt2_implementation.model.PaymentDetails;

@Service
public class PaymentService {
    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
