package com.github.hojoungjang.pt2_implementation.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.hojoungjang.pt2_implementation.exception.AccountNotFoundException;
import com.github.hojoungjang.pt2_implementation.model.Account;
import com.github.hojoungjang.pt2_implementation.repository.AccountRepository;

@Service
public class TransferService {
    private final AccountRepository accountRepo;

    public TransferService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountRepo.findById(idSender).orElseThrow(() -> new AccountNotFoundException());
        Account receiver = accountRepo.findById(idReceiver).orElseThrow(() -> new AccountNotFoundException());

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepo.changeAmount(idSender, senderNewAmount);
        accountRepo.changeAmount(idReceiver, receiverNewAmount);

        // Throw error to test transaction is working
        // throw new RuntimeException("Oh no something went wrong!");
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepo.findAccountsByName(name);
    }
}
