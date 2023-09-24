package com.pizzaria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.EnderecoDto;
import main.entity.Cliente;

@SpringBootTest
class EnderecoDtoTest {

    private EnderecoDto enderecoDto;

    @BeforeEach
    public void setUp() {
        enderecoDto = new EnderecoDto();
    }

    @Test
    void testSetId() {
        enderecoDto.setId(1L);
        assertEquals(1L, enderecoDto.getId());
    }

    @Test
    void testSetRua() {
        enderecoDto.setRua("Rua Teste");
        assertEquals("Rua Teste", enderecoDto.getRua());
    }

    @Test
    void testSetNumero() {
        enderecoDto.setNumero(123L);
        assertEquals(123L, enderecoDto.getNumero());
    }

    @Test
    void testSetCliente() {
        Cliente cliente= new Cliente();
        cliente.setId(1L);
        enderecoDto.setCliente(cliente);
        assertEquals(1L, enderecoDto.getCliente().getId());
    }

    @Test
    void testGetId() {
        enderecoDto.setId(1L);
        assertEquals(1L, enderecoDto.getId());
    }

    @Test
    void testGetRua() {
        enderecoDto.setRua("Rua Teste");
        assertEquals("Rua Teste", enderecoDto.getRua());
    }

    @Test
    void testGetNumero() {
        enderecoDto.setNumero(123L);
        assertEquals(123L, enderecoDto.getNumero());
    }

    @Test
    void testGetCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        enderecoDto.setCliente(cliente);
        assertEquals(1L, enderecoDto.getCliente().getId());
    }

    @Test
    void testConstructorVazio() {
        assertNull(enderecoDto.getId());
        assertNull(enderecoDto.getRua());
        assertNull(enderecoDto.getNumero());
        assertNull(enderecoDto.getCliente());
    }
}
