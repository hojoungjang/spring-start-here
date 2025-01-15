package com.github.hojoungjang.pt2_implementation;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.github.hojoungjang.pt2_implementation.model.Account;
import com.github.hojoungjang.pt2_implementation.repository.AccountRepository;
import com.github.hojoungjang.pt2_implementation.service.TransferService;

@SpringBootTest
public class TransferServiceSpringIntegrationTests {
    
    @MockitoBean
    private AccountRepository accountRepo;

    @Autowired
    private TransferService transferService;

    @Test
    void transferMoneyTest() {
        Account sender = new Account();
        sender.setId(1L);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setAmount(new BigDecimal(1000));

        when(accountRepo.findById(1L)).thenReturn(Optional.of(sender));
        when(accountRepo.findById(2L)).thenReturn(Optional.of(receiver));

        transferService.transferMoney(1L, 2L, new BigDecimal(100));

        verify(accountRepo).changeAmount(1L, new BigDecimal(900));
        verify(accountRepo).changeAmount(2L, new BigDecimal(1100));
    }
}
