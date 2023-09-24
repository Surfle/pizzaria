package com.pizzaria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.ClienteDto;

@SpringBootTest
class ClienteDtoTest{

    private ClienteDto clienteDto;

    @BeforeEach
    public void setUp() {
        clienteDto = new ClienteDto();
    }

    @Test
    void testSetId() {
        clienteDto.setId(1L);
        assertEquals(1L, clienteDto.getId());
    }

    @Test
    void testSetNome() {
        clienteDto.setNome("Nome do Cliente");
        assertEquals("Nome do Cliente", clienteDto.getNome());
    }

    @Test
    void testSetNumero() {
        clienteDto.setNumero("123456789");
        assertEquals("123456789", clienteDto.getNumero());
    }
    
    @Test
    void testGetId() {
        clienteDto.setId(1L);
        assertEquals(1L, clienteDto.getId());
    }

    @Test
    void testGetNome() {
        clienteDto.setNome("Nome do Cliente");
        assertEquals("Nome do Cliente", clienteDto.getNome());
    }

    @Test
    void testGetNumero() {
        clienteDto.setNumero("123456789");
        assertEquals("123456789", clienteDto.getNumero());
    }
    
    @Test
    void testConstructorVazio() {
        assertNull(clienteDto.getId());
        assertNull(clienteDto.getNome());
        assertNull(clienteDto.getNumero());
    }
}