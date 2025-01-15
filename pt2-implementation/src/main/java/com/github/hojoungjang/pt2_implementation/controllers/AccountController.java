package com.github.hojoungjang.pt2_implementation.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.hojoungjang.pt2_implementation.dto.TransferRequest;
import com.github.hojoungjang.pt2_implementation.model.Account;
import com.github.hojoungjang.pt2_implementation.service.TransferService;

@RestController
public class AccountController {
    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(
        @RequestBody TransferRequest transferRequest
    ) {
        transferService.transferMoney(
            transferRequest.getSenderAccountId(),
            transferRequest.getReceiverAccountId(),
            transferRequest.getAmount()
        );
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
        @RequestParam(required = false) String name
    ) {
        if (name != null) {
            return transferService.findAccountsByName(name);
        }
        return transferService.getAllAccounts();
    }
}
