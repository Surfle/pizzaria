package com.pizzaria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.IngredienteDto;

@SpringBootTest
class IngredienteDtoTest {

    private IngredienteDto ingredienteDto;

    @BeforeEach
    public void setUp() {
        ingredienteDto = new IngredienteDto();
    }

    @Test
    void testSetId() {
        ingredienteDto.setId(1L);
        assertEquals(1L, ingredienteDto.getId());
    }

    @Test
    void testSetNome() {
        ingredienteDto.setNome("Queijo");
        assertEquals("Queijo", ingredienteDto.getNome());
    }

    @Test
    void testSetValor() {
        ingredienteDto.setValor(5.99);
        assertEquals(5.99, ingredienteDto.getValor());
    }

    @Test
    void testGetId() {
        ingredienteDto.setId(1L);
        assertEquals(1L, ingredienteDto.getId());
    }

    @Test
    void testGetNome() {
        ingredienteDto.setNome("Queijo");
        assertEquals("Queijo", ingredienteDto.getNome());
    }

    @Test
    void testGetValor() {
        ingredienteDto.setValor(5.99);
        assertEquals(5.99, ingredienteDto.getValor());
    }

    @Test
    void testConstructorVazio() {
        assertNull(ingredienteDto.getId());
        assertNull(ingredienteDto.getNome());
        assertEquals(0.0, ingredienteDto.getValor());
    }
}


