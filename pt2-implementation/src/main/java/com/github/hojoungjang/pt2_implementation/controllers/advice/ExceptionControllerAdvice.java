package com.github.hojoungjang.pt2_implementation.controllers.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.hojoungjang.pt2_implementation.exception.NotEnoughMoneyException;
import com.github.hojoungjang.pt2_implementation.model.ErrorDetails;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorDetails> notEnoughMoneyExceptionHandler(NotEnoughMoneyException e) {
        System.out.println(e);
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Not enough money to process payment.");
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
