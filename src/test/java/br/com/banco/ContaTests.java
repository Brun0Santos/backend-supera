package br.com.banco;

import br.com.banco.exceptions.IdContaNotFoundException;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Testes da classe ContaService")
public class ContaTests {
    @Mock
    private TransferenciaRepository repository;

    @InjectMocks
    ContaService contaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveraLancarExceptionPorContaInformadaNaoExistenteTest() {
        when(repository.findContaBancaria(5)).thenReturn(Optional.of(Collections.emptyList()));
        IdContaNotFoundException idContaNotFoundException = assertThrows(IdContaNotFoundException.class,
                () -> contaService.getDadosTransferencia(5));
        verify(repository).findContaBancaria(5);
        assertEquals("ContaID informada nao encontrada", idContaNotFoundException.getMessage());
    }
}
