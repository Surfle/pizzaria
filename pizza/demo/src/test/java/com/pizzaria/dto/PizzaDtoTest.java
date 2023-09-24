package com.pizzaria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.PizzaDto;

@SpringBootTest
class PizzaDtoTest {

    private PizzaDto pizzaDto;

    @BeforeEach
    public void setUp() {
        pizzaDto = new PizzaDto();
    }

    @Test
    void testSetId() {
        pizzaDto.setId(1L);
        assertEquals(1L, pizzaDto.getId());
    }

    @Test
    void testSetTamanho() {
        pizzaDto.setTamanho(12L);
        assertEquals(12L, pizzaDto.getTamanho());
    }

    @Test
    void testGetId() {
        pizzaDto.setId(1L);
        assertEquals(1L, pizzaDto.getId());
    }

    @Test
    void testGetTamanho() {
        pizzaDto.setTamanho(12L);
        assertEquals(12L, pizzaDto.getTamanho());
    }

    @Test
    void testConstructorVazio() {
        assertNull(pizzaDto.getId());
        assertNull(pizzaDto.getTamanho());
    }
}
