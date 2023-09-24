package com.pizzaria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.ProdutoDto;

@SpringBootTest
class ProdutoDtoTest {

    private ProdutoDto produtoDto;

    @BeforeEach
    public void setUp() {
        produtoDto = new ProdutoDto();
    }

    @Test
    void testSetId() {
        produtoDto.setId(1L);
        assertEquals(1L, produtoDto.getId());
    }

    @Test
    void testSetNome() {
        produtoDto.setNome("Produto Teste");
        assertEquals("Produto Teste", produtoDto.getNome());
    }

    @Test
    void testSetValor() {
        produtoDto.setValor(10.5);
        assertEquals(10.5, produtoDto.getValor(), 0.001);
    }

    @Test
    void testGetId() {
        produtoDto.setId(1L);
        assertEquals(1L, produtoDto.getId());
    }

    @Test
    void testGetNome() {
        produtoDto.setNome("Produto Teste");
        assertEquals("Produto Teste", produtoDto.getNome());
    }

    @Test
    void testGetValor() {
        produtoDto.setValor(10.5);
        assertEquals(10.5, produtoDto.getValor(), 0.001);
    }

    @Test
    void testConstructorVazio() {
        assertNull(produtoDto.getId());
        assertNull(produtoDto.getNome());
        assertEquals(0.0, produtoDto.getValor(), 0.001);
    }
}
