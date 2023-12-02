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

import main.controller.ClienteController;
import main.dto.ClienteDto;
import main.service.ClienteService;

@SpringBootTest
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {
        List<ClienteDto> clientesSimulados = new ArrayList<>();
        ClienteDto cliente1 = new ClienteDto();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        clientesSimulados.add(cliente1);

        when(clienteService.findAll()).thenReturn(clientesSimulados);

        ResponseEntity<List<ClienteDto>> resultado = clienteController.findAll();

        verify(clienteService).findAll();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(clientesSimulados.size(), resultado.getBody().size());
        assertEquals(clientesSimulados.get(0).getNome(), resultado.getBody().get(0).getNome());
    
        when(clienteService.findAll()).thenThrow(new RuntimeException("Erro ao buscar clientes"));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> clienteController.findAll());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Erro ao buscar clientes", exception.getReason());
    
    }

    @Test
    void testFindById() {
        ClienteDto clienteSimulado = new ClienteDto();
        clienteSimulado.setId(1L);
        clienteSimulado.setNome("Nome do Cliente");

        when(clienteService.findById(1L)).thenReturn(clienteSimulado);

        ResponseEntity<ClienteDto> resultado = clienteController.findById(1L);

        verify(clienteService).findById(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(clienteSimulado.getId(), resultado.getBody().getId());
        assertEquals(clienteSimulado.getNome(), resultado.getBody().getNome());
    
        when(clienteService.findById(2L)).thenThrow(new RuntimeException("Cliente não encontrado"));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> clienteController.findById(2L));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Cliente não encontrado", exception.getReason());
    }

    @Test
    void testInclude() {
        ClienteDto clienteDTOSimulado = new ClienteDto();
        clienteDTOSimulado.setNome("Nome do Cliente");

        when(clienteService.include(clienteDTOSimulado)).thenReturn(clienteDTOSimulado);

        ResponseEntity<ClienteDto> resultado = clienteController.include(clienteDTOSimulado);

        verify(clienteService).include(clienteDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(clienteDTOSimulado.getNome(), resultado.getBody().getNome());
   
        when(clienteService.include(null)).thenThrow(new RuntimeException("Dados inválidos"));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> clienteController.include(null));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Dados inválidos", exception.getReason());
    }

    @Test
    void testEdit() {
        ClienteDto clienteDTOSimulado = new ClienteDto();
        clienteDTOSimulado.setId(1L);
        clienteDTOSimulado.setNome("Novo Nome do Cliente");

        when(clienteService.edit(1L, clienteDTOSimulado)).thenReturn(clienteDTOSimulado);

        ResponseEntity<ClienteDto> resultado = clienteController.edit(1L, clienteDTOSimulado);

        verify(clienteService).edit(1L, clienteDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(clienteDTOSimulado.getNome(), resultado.getBody().getNome());
    
        when(clienteService.edit(2L, null)).thenThrow(new RuntimeException("Dados inválidos"));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> clienteController.edit(2L, null));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Dados inválidos", exception.getReason());
    }

    @Test
    void testDelete() {
        ClienteDto clienteDTOSimulado = new ClienteDto();
        clienteDTOSimulado.setId(1L);
        clienteDTOSimulado.setNome("Cliente Removido");

        when(clienteService.delete(1L)).thenReturn(clienteDTOSimulado);

        ResponseEntity<ClienteDto> resultado = clienteController.delete(1L);

        verify(clienteService).delete(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(clienteDTOSimulado.getNome(), resultado.getBody().getNome());
    
        when(clienteService.delete(2L)).thenThrow(new RuntimeException("Cliente não encontrado"));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> clienteController.delete(2L));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Cliente não encontrado", exception.getReason());
    }
}