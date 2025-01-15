package com.github.hojoungjang.pt2_implementation;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.hojoungjang.pt2_implementation.exception.AccountNotFoundException;
import com.github.hojoungjang.pt2_implementation.model.Account;
import com.github.hojoungjang.pt2_implementation.repository.AccountRepository;
import com.github.hojoungjang.pt2_implementation.service.TransferService;

@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTests {
    @Mock
    private AccountRepository accountRepo;

    @InjectMocks
    private TransferService transferService;

    @Test
    @DisplayName("Test the amount is transferred from one account to another if no exception occurs.")
    public void transferMoneyHappyFlow() {
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

    @Test
    @DisplayName("Test AccountNotFoundException is thrown")
    public void transferMoneyAccountNotFound() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepo.findById(sender.getId())).willReturn(Optional.of(sender));
        given(accountRepo.findById(2L)).willReturn(Optional.empty());

        assertThrows(
            AccountNotFoundException.class, 
            () -> transferService.transferMoney(1, 2, new BigDecimal(100))
        );

        verify(accountRepo, never()).changeAmount(anyLong(), any());
    }
}
