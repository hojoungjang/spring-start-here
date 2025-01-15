package com.github.hojoungjang.pt2_implementation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.hojoungjang.pt2_implementation.model.Account;
import com.github.hojoungjang.pt2_implementation.repository.AccountRepository;
import com.github.hojoungjang.pt2_implementation.service.TransferService;

public class TransferServiceUnitTests {
    @Test
    @DisplayName("Test the amount is transferred from one account to another if no exception occurs.")
    public void transferMoneyHappyFlow() {
        AccountRepository accountRepo = mock(AccountRepository.class);
        TransferService transferService = new TransferService(accountRepo);

        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2);
        receiver.setAmount(new BigDecimal(1000));

        given(accountRepo.findById(sender.getId())).willReturn(Optional.of(sender));
        given(accountRepo.findById(receiver.getId())).willReturn(Optional.of(receiver));

        transferService.transferMoney(sender.getId(), receiver.getId(), new BigDecimal(100));

        verify(accountRepo).changeAmount(sender.getId(), new BigDecimal(900));
        verify(accountRepo).changeAmount(receiver.getId(), new BigDecimal(1100));
    }
}
