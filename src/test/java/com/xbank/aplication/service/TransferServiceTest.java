package com.xbank.aplication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xbank.aplication.model.ContaPF;
import com.xbank.aplication.model.Transfer;
import com.xbank.aplication.repositories.AccountRepository;
import com.xbank.aplication.repositories.TransferRepository;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    void deveExecutarTransferenciaComSucesso() {

        //cria contas com saldo suficiente
        ContaPF source = new ContaPF();
        source.setId(1L);
        source.setBalance(new BigDecimal("1000"));

        ContaPF destination = new ContaPF();
        destination.setId(2L);
        destination.setBalance(new BigDecimal("500"));

        BigDecimal amount = new BigDecimal("200");

        // simula busca das contas no repositry
        when(accountRepository.findById(1L)).thenReturn(Optional.of(source));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destination));

        //executa a transferencia
        transferService.executeTransfer(1L, 2L, amount);

        // verifica se os saldos foram atualizados
        assertEquals(new BigDecimal("800"), source.getBalance());
        assertEquals(new BigDecimal("700"), destination.getBalance());

        // verifica se a transferencia foi salva
        verify(transferRepository).save(any(Transfer.class));
    }

    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente() {

        //cria conta com saldo insuficiente
        ContaPF source = new ContaPF();
        source.setId(1L);
        source.setBalance(new BigDecimal("100"));

        ContaPF destination = new ContaPF();
        destination.setId(2L);
        destination.setBalance(new BigDecimal("500"));

        BigDecimal amount = new BigDecimal("200");

        // busca as contas
        when(accountRepository.findById(1L)).thenReturn(Optional.of(source));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destination));

        // verifica se excecao e lancada ao tentar transferir valor maior que o saldo
        assertThrows(IllegalArgumentException.class, () -> {
            transferService.executeTransfer(1L, 2L, amount);
        });
    }
}
