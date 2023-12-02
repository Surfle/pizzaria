package com.pizzaria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.FuncionarioDto;

@SpringBootTest
class FuncionarioDtoTest {

    private FuncionarioDto funcionarioDto;

    @BeforeEach
    public void setUp() {
        funcionarioDto = new FuncionarioDto();
    }

    @Test
    void testSetId() {
        funcionarioDto.setId(1L);
        assertEquals(1L, funcionarioDto.getId());
    }

    @Test
    void testSetNome() {
        funcionarioDto.setNome("Nome do Funcionário");
        assertEquals("Nome do Funcionário", funcionarioDto.getNome());
    }

    @Test
    void testGetId() {
        funcionarioDto.setId(1L);
        assertEquals(1L, funcionarioDto.getId());
    }

    @Test
    void testGetNome() {
        funcionarioDto.setNome("Nome do Funcionário");
        assertEquals("Nome do Funcionário", funcionarioDto.getNome());
    }

    @Test
    void testConstructorVazio() {
        assertNull(funcionarioDto.getId());
        assertNull(funcionarioDto.getNome());
    }
}





