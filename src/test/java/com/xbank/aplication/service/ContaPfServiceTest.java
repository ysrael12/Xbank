package com.xbank.aplication.service;

import com.xbank.aplication.model.ContaPF;
import com.xbank.aplication.model.User;
import com.xbank.aplication.repositories.ContaPfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContaPfServiceTest {

    @Mock
    private ContaPfRepository contaPfRepository; // mock do repositório

    @InjectMocks
    private ContaPfService contaPfService; // servico que sera testado

    @BeforeEach
    public void setUp() {
        // inicializa os mocks antes de cada teste
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateContaPf() {
        User user = new User();
        user.setCPF("12345678900");

        ContaPF conta = new ContaPF();

        // simula retorno do repositorio quando salvar
        ContaPF contaSaved = new ContaPF();
        contaSaved.setId(1L);
        contaSaved.setCpf("12345678900");
        contaSaved.setBankName("XBank");
        contaSaved.setBalance(BigDecimal.ZERO);

        when(contaPfRepository.save(any(ContaPF.class))).thenReturn(contaSaved);

        ContaPF result = contaPfService.createContaPf(conta, user);

        // verifica se os dados retornados estao certos
        assertEquals(1L, result.getId());
        assertEquals("12345678900", result.getCpf());
        assertEquals("XBank", result.getBankName());
        assertEquals(BigDecimal.ZERO, result.getBalance());

        verify(contaPfRepository).save(conta); // Verifica se save foi chamado
    }

    @Test
    public void testDeposit() {
        ContaPF conta = new ContaPF();
        conta.setId(1L);
        conta.setBalance(BigDecimal.ZERO);

        when(contaPfRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaPfRepository.save(any(ContaPF.class))).thenAnswer(i -> i.getArgument(0));

        ContaPF result = contaPfService.deposit(1L, new BigDecimal("100.00"));

        assertEquals(new BigDecimal("100.00"), result.getBalance());

        verify(contaPfRepository).findById(1L);
        verify(contaPfRepository).save(conta);
    }

    @Test
    public void testWithdraw() {
        ContaPF conta = new ContaPF();
        conta.setId(1L);
        conta.setBalance(new BigDecimal("200.00"));

        when(contaPfRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaPfRepository.save(any(ContaPF.class))).thenAnswer(i -> i.getArgument(0));

        ContaPF result = contaPfService.withdraw(1L, new BigDecimal("50.00"));
        assertEquals(new BigDecimal("150.00"), result.getBalance());
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        ContaPF conta = new ContaPF();
        conta.setId(1L);
        conta.setBalance(new BigDecimal("30.00"));

        when(contaPfRepository.findById(1L)).thenReturn(Optional.of(conta));

        // lanca excecao por saldo insuficiente
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> contaPfService.withdraw(1L, new BigDecimal("50.00")));

        assertEquals("Saldo insuficiente para realizar o saque.", exception.getMessage());
    }

    @Test
    public void testExistsByCpf() {
        when(contaPfRepository.findByCpf("12345678900")).thenReturn(List.of(new ContaPF()));

        assertTrue(contaPfService.existsByCpf("12345678900"));
    }

    @Test
    public void testDeleteById() {
        contaPfService.deleteById(1L);
        verify(contaPfRepository).deleteById(1L);
    }

    @Test
    public void testFindAll() {
        when(contaPfRepository.findAll()).thenReturn(List.of(new ContaPF(), new ContaPF()));

        List<ContaPF> contas = (List<ContaPF>) contaPfService.findAll();
        assertEquals(2, contas.size());
    }
}
