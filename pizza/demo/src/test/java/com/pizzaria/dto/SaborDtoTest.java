package com.pizzaria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.SaborDto;

@SpringBootTest
class SaborDtoTest {

    private SaborDto saborDto;

    @BeforeEach
    public void setUp() {
        saborDto = new SaborDto();
    }

    @Test
    void testSetId() {
        saborDto.setId(1L);
        assertEquals(1L, saborDto.getId());
    }

    @Test
    void testSetNome() {
        saborDto.setNome("Sabor Teste");
        assertEquals("Sabor Teste", saborDto.getNome());
    }

    @Test
    void testSetValor() {
        saborDto.setValor(8.99);
        assertEquals(8.99, saborDto.getValor(), 0.001);
    }

    @Test
    void testGetId() {
        saborDto.setId(1L);
        assertEquals(1L, saborDto.getId());
    }

    @Test
    void testGetNome() {
        saborDto.setNome("Sabor Teste");
        assertEquals("Sabor Teste", saborDto.getNome());
    }

    @Test
    void testGetValor() {
        saborDto.setValor(8.99);
        assertEquals(8.99, saborDto.getValor(), 0.001);
    }

    @Test
    void testConstructorVazio() {
        assertNull(saborDto.getId());
        assertNull(saborDto.getNome());
        assertEquals(0.0, saborDto.getValor(), 0.001);
    }
}