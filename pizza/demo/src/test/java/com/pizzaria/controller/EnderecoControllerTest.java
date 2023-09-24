package com.pizzaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import main.controller.EnderecoController;
import main.dto.EnderecoDto;
import main.service.EnderecoService;

@SpringBootTest
class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {
        List<EnderecoDto> enderecosSimulados = new ArrayList<>();
        EnderecoDto endereco1 = new EnderecoDto();
        endereco1.setId(1L);
        endereco1.setRua("Rua 1");
        enderecosSimulados.add(endereco1);

        when(enderecoService.findAll()).thenReturn(enderecosSimulados);

        ResponseEntity<List<EnderecoDto>> resultado = enderecoController.findAll();

        verify(enderecoService).findAll();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(enderecosSimulados.size(), resultado.getBody().size());
        assertEquals(enderecosSimulados.get(0).getRua(), resultado.getBody().get(0).getRua());
    }

    @Test
    void testFindById() {
        EnderecoDto enderecoSimulado = new EnderecoDto();
        enderecoSimulado.setId(1L);
        enderecoSimulado.setRua("Rua do Endereco");

        when(enderecoService.findById(1L)).thenReturn(enderecoSimulado);

        ResponseEntity<EnderecoDto> resultado = enderecoController.findById(1L);

        verify(enderecoService).findById(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(enderecoSimulado.getId(), resultado.getBody().getId());
        assertEquals(enderecoSimulado.getRua(), resultado.getBody().getRua());
    }

    @Test
    void testInclude() {
        EnderecoDto enderecoDTOSimulado = new EnderecoDto();
        enderecoDTOSimulado.setRua("Rua do Endereco");

        when(enderecoService.include(1L, enderecoDTOSimulado)).thenReturn(enderecoDTOSimulado);

        ResponseEntity<EnderecoDto> resultado = enderecoController.include(1L, enderecoDTOSimulado);

        verify(enderecoService).include(1L, enderecoDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(enderecoDTOSimulado.getRua(), resultado.getBody().getRua());
    
        when(enderecoService.include(1L, enderecoDTOSimulado)).thenThrow(new RuntimeException("Erro ao incluir endereço"));
        
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> enderecoController.include(1L, enderecoDTOSimulado));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Erro ao incluir endereço", exception.getReason());
    }

    @Test
    void testEdit() {
        EnderecoDto enderecoDTOSimulado = new EnderecoDto();
        enderecoDTOSimulado.setId(1L);
        enderecoDTOSimulado.setRua("Nova Rua do Endereco");

        when(enderecoService.edit(1L, enderecoDTOSimulado)).thenReturn(enderecoDTOSimulado);

        ResponseEntity<EnderecoDto> resultado = enderecoController.edit(1L, enderecoDTOSimulado);

        verify(enderecoService).edit(1L, enderecoDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(enderecoDTOSimulado.getRua(), resultado.getBody().getRua());
    }

    @Test
    void testDelete() {
        EnderecoDto enderecoDTOSimulado = new EnderecoDto();
        enderecoDTOSimulado.setId(1L);
        enderecoDTOSimulado.setRua("Endereco Removido");

        when(enderecoService.delete(1L)).thenReturn(enderecoDTOSimulado);

        ResponseEntity<EnderecoDto> resultado = enderecoController.delete(1L);

        verify(enderecoService).delete(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(enderecoDTOSimulado.getRua(), resultado.getBody().getRua());
    }
}