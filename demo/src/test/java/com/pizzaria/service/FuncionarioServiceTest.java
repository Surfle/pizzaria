package com.pizzaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.FuncionarioDto;
import main.entity.Funcionario;
import main.repository.FuncionarioRepository;
import main.service.FuncionarioService;

@SpringBootTest
class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testFindAll() {

        List<Funcionario> funcionariosSimulados = new ArrayList<>();
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setId(1L);
        funcionario1.setNome("Funcionario 1");
        funcionariosSimulados.add(funcionario1);

        when(funcionarioRepository.findAll()).thenReturn(funcionariosSimulados);

        List<FuncionarioDto> resultado = funcionarioService.findAll();

        verify(funcionarioRepository).findAll();

        assertEquals(funcionariosSimulados.size(), resultado.size());
        assertEquals(funcionariosSimulados.get(0).getNome(), resultado.get(0).getNome());
    }

    @Test
    void testFindById() {

        Funcionario funcionarioSimulado = new Funcionario();
        funcionarioSimulado.setId(1L);
        funcionarioSimulado.setNome("Nome do Funcionario");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionarioSimulado));

        FuncionarioDto resultado = funcionarioService.findById(1L);

        verify(funcionarioRepository).findById(1L);

        assertEquals(funcionarioSimulado.getNome(), resultado.getNome());
    }

    @Test
    void testInclude() {

        FuncionarioDto funcionarioDTOSimulado = new FuncionarioDto();
        funcionarioDTOSimulado.setNome("Nome do Funcionario");

        FuncionarioDto resultado = funcionarioService.include(funcionarioDTOSimulado);

        verify(funcionarioRepository).save(any(Funcionario.class));

        assertEquals(funcionarioDTOSimulado.getNome(), resultado.getNome());
    }

    @Test
    void testEdit() {

        FuncionarioDto funcionarioDTOSimulado = new FuncionarioDto();
        funcionarioDTOSimulado.setId(1L);
        funcionarioDTOSimulado.setNome("Novo Nome do Funcionario");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(new Funcionario()));

        FuncionarioDto resultado = funcionarioService.edit(1L, funcionarioDTOSimulado);

        verify(funcionarioRepository).findById(1L);

        verify(funcionarioRepository).save(any(Funcionario.class));

        assertEquals(funcionarioDTOSimulado.getNome(), resultado.getNome());
    }

    @Test
    void testDelete() {

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(new Funcionario()));

        FuncionarioDto resultado = funcionarioService.delete(1L);

        verify(funcionarioRepository).findById(1L);

        verify(funcionarioRepository).deleteById(1L);

        assertNotNull(resultado);
    }
}