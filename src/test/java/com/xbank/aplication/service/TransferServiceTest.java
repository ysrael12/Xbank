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

import com.xbank.aplication.model.*;
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

    // transferencia normal
    @Test
    void deveExecutarTransferenciaComSucesso() {
        ContaPF source = new ContaPF();
        source.setId(1L);
        source.setBalance(new BigDecimal("1000"));

        ContaPF destination = new ContaPF();
        destination.setId(2L);
        destination.setBalance(new BigDecimal("500"));

        BigDecimal amount = new BigDecimal("200");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(source));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destination));

        transferService.executeTransfer(1L, 2L, amount);

        assertEquals(new BigDecimal("800"), source.getBalance());
        assertEquals(new BigDecimal("700"), destination.getBalance());
        verify(transferRepository).save(any(Transfer.class));
    }

    // saldo insuficiente
    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente() {
        ContaPF source = new ContaPF();
        source.setId(1L);
        source.setBalance(new BigDecimal("100"));

        ContaPF destination = new ContaPF();
        destination.setId(2L);
        destination.setBalance(new BigDecimal("500"));

        BigDecimal amount = new BigDecimal("200");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(source));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destination));

        assertThrows(IllegalArgumentException.class, () -> {
            transferService.executeTransfer(1L, 2L, amount);
        });
    }

    // pix vlaido
    @Test
    void deveExecutarPixComValorValido() {
        ContaPF source = new ContaPF();
        source.setBalance(new BigDecimal("1000"));

        ContaPF destination = new ContaPF();
        destination.setBalance(new BigDecimal("1300"));

        BigDecimal amount = new BigDecimal("2000");

        Pix pix = new Pix(source, destination, amount);
        pix.setAmount(amount); // valida limite

        assertEquals(new BigDecimal("2000"), pix.getAmount());
    }

    // pix invalido
    @Test
    void deveLancarExcecaoQuandoPixExcedeLimite() {
        ContaPF source = new ContaPF();
        ContaPF destination = new ContaPF();
        BigDecimal amount = new BigDecimal("6043");

        Pix pix = new Pix(source, destination, amount);

        assertThrows(IllegalArgumentException.class, () -> {
            pix.setAmount(amount);
        });
    }

    // TED valido
    @Test
    void deveExecutarTedComValorValido() {
        ContaPF source = new ContaPF();
        ContaPF destination = new ContaPF();
        BigDecimal amount = new BigDecimal("1500");

        Ted ted = new Ted(source, destination, amount);
        ted.setAmount(amount);

        assertEquals(new BigDecimal("1500"), ted.getAmount());
    }

    // TED invalido
    @Test
    void deveLancarExcecaoQuandoTedAbaixoDoMinimo() {
        ContaPF source = new ContaPF();
        ContaPF destination = new ContaPF();
        BigDecimal amount = new BigDecimal("800");

        Ted ted = new Ted(source, destination, amount);

        assertThrows(IllegalArgumentException.class, () -> {
            ted.setAmount(amount);
        });
    }

    // Bbleto sem destino
    @Test
    void deveLancarExcecaoAoDefinirDestinoEmBoleto() {
        ContaPF source = new ContaPF();
        ContaPF destination = new ContaPF();
        BigDecimal amount = new BigDecimal("300");

        Boleto boleto = new Boleto(source, amount);

        assertThrows(UnsupportedOperationException.class, () -> {
            boleto.setDestinationAccount(destination);
        });
    }

    // deposito sem origem
    @Test
    void deveLancarExcecaoAoDefinirOrigemEmDeposito() {
        ContaPF source = new ContaPF();
        ContaPF destination = new ContaPF();
        BigDecimal amount = new BigDecimal("500");

        Deposito deposito = new Deposito(source, destination, amount);

        assertThrows(UnsupportedOperationException.class, () -> {
            deposito.setSourceAccount(source);
        });
    }
}
