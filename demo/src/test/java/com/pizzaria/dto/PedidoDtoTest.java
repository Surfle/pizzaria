package com.pizzaria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.PedidoDto;
import main.entity.Cliente;
import main.entity.Funcionario;

@SpringBootTest
class PedidoDtoTest {

    private PedidoDto pedidoDto;

    @BeforeEach
    public void setUp() {
        pedidoDto = new PedidoDto();
    }

    @Test
    void testSetId() {
        pedidoDto.setId(1L);
        assertEquals(1L, pedidoDto.getId());
    }

    @Test
    void testSetValor() {
        pedidoDto.setValor(25.99);
        assertEquals(25.99, pedidoDto.getValor());
    }

    @Test
    void testSetObservacao() {
        pedidoDto.setObservacao("Sem cebola");
        assertEquals("Sem cebola", pedidoDto.getObservacao());
    }

    @Test
    void testSetEntrega() {
        pedidoDto.setEntrega(true);
        assertEquals(true, pedidoDto.isEntrega());
    }

    @Test
    void testSetFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        pedidoDto.setFuncionario(funcionario);
        assertEquals(funcionario, pedidoDto.getFuncionario());
    }

    @Test
    void testSetCliente() {
        Cliente cliente= new Cliente();
        cliente.setId(1L);
        pedidoDto.setCliente(cliente);
        assertEquals(cliente, pedidoDto.getCliente());
    }

    @Test
    void testGetId() {
        pedidoDto.setId(1L);
        assertEquals(1L, pedidoDto.getId());
    }

    @Test
    void testGetValor() {
        pedidoDto.setValor(25.99);
        assertEquals(25.99, pedidoDto.getValor());
    }

    @Test
    void testGetObservacao() {
        pedidoDto.setObservacao("Sem cebola");
        assertEquals("Sem cebola", pedidoDto.getObservacao());
    }

    @Test
    void testIsEntrega() {
        pedidoDto.setEntrega(true);
        assertEquals(true, pedidoDto.isEntrega());
    }

    @Test
    void testGetFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        pedidoDto.setFuncionario(funcionario);
        assertEquals(funcionario, pedidoDto.getFuncionario());
    }

    @Test
    void testGetCliente() {
        Cliente cliente= new Cliente();
        cliente.setId(1L);
        pedidoDto.setCliente(cliente);
        assertEquals(cliente, pedidoDto.getCliente());
    }

    @Test
    void testConstructorVazio() {
        assertNull(pedidoDto.getId());
        assertEquals(0.0, pedidoDto.getValor());
        assertNull(pedidoDto.getObservacao());
        assertEquals(false, pedidoDto.isEntrega());
        assertNull(pedidoDto.getFuncionario());
        assertNull(pedidoDto.getCliente());
    }
}