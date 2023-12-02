package com.pizzaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import main.controller.FuncionarioController;
import main.dto.FuncionarioDto;
import main.service.FuncionarioService;

@SpringBootTest
class FuncionarioControllerTest {

    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {
        List<FuncionarioDto> funcionariosSimulados = new ArrayList<>();
        FuncionarioDto funcionario1 = new FuncionarioDto();
        funcionario1.setId(1L);
        funcionario1.setNome("Funcionario 1");
        funcionariosSimulados.add(funcionario1);

        when(funcionarioService.findAll()).thenReturn(funcionariosSimulados);

        ResponseEntity<List<FuncionarioDto>> resultado = funcionarioController.findAll();

        verify(funcionarioService).findAll();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(funcionariosSimulados.size(), resultado.getBody().size());
        assertEquals(funcionariosSimulados.get(0).getNome(), resultado.getBody().get(0).getNome());
    }

    @Test
    void testFindById() {
        FuncionarioDto funcionarioSimulado = new FuncionarioDto();
        funcionarioSimulado.setId(1L);
        funcionarioSimulado.setNome("Nome do Funcionario");

        when(funcionarioService.findById(1L)).thenReturn(funcionarioSimulado);

        ResponseEntity<FuncionarioDto> resultado = funcionarioController.findById(1L);

        verify(funcionarioService).findById(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(funcionarioSimulado.getId(), resultado.getBody().getId());
        assertEquals(funcionarioSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testInclude() {
        FuncionarioDto funcionarioDTOSimulado = new FuncionarioDto();
        funcionarioDTOSimulado.setNome("Nome do Funcionario");

        when(funcionarioService.include(funcionarioDTOSimulado)).thenReturn(funcionarioDTOSimulado);

        ResponseEntity<FuncionarioDto> resultado = funcionarioController.include(funcionarioDTOSimulado);

        verify(funcionarioService).include(funcionarioDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(funcionarioDTOSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testEdit() {
        FuncionarioDto funcionarioDTOSimulado = new FuncionarioDto();
        funcionarioDTOSimulado.setId(1L);
        funcionarioDTOSimulado.setNome("Novo Nome do Funcionario");

        when(funcionarioService.edit(1L, funcionarioDTOSimulado)).thenReturn(funcionarioDTOSimulado);

        ResponseEntity<FuncionarioDto> resultado = funcionarioController.edit(1L, funcionarioDTOSimulado);

        verify(funcionarioService).edit(1L, funcionarioDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(funcionarioDTOSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testDelete() {
        FuncionarioDto funcionarioDTOSimulado = new FuncionarioDto();
        funcionarioDTOSimulado.setId(1L);
        funcionarioDTOSimulado.setNome("Funcionario Removido");

        when(funcionarioService.delete(1L)).thenReturn(funcionarioDTOSimulado);

        ResponseEntity<FuncionarioDto> resultado = funcionarioController.delete(1L);

        verify(funcionarioService).delete(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(funcionarioDTOSimulado.getNome(), resultado.getBody().getNome());
    }
}