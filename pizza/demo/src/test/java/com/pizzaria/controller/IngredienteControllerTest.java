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

import main.controller.IngredienteController;
import main.dto.IngredienteDto;
import main.service.IngredienteService;

@SpringBootTest
class IngredienteControllerTest {

    @InjectMocks
    private IngredienteController ingredienteController;

    @Mock
    private IngredienteService ingredienteService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {
        List<IngredienteDto> ingredientesSimulados = new ArrayList<>();
        IngredienteDto ingrediente1 = new IngredienteDto();
        ingrediente1.setId(1L);
        ingrediente1.setNome("Ingrediente 1");
        ingredientesSimulados.add(ingrediente1);

        when(ingredienteService.findAll()).thenReturn(ingredientesSimulados);

        ResponseEntity<List<IngredienteDto>> resultado = ingredienteController.findAll();

        verify(ingredienteService).findAll();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(ingredientesSimulados.size(), resultado.getBody().size());
        assertEquals(ingredientesSimulados.get(0).getNome(), resultado.getBody().get(0).getNome());
    }

    @Test
    void testFindById() {
        IngredienteDto ingredienteSimulado = new IngredienteDto();
        ingredienteSimulado.setId(1L);
        ingredienteSimulado.setNome("Nome do Ingrediente");

        when(ingredienteService.findById(1L)).thenReturn(ingredienteSimulado);

        ResponseEntity<IngredienteDto> resultado = ingredienteController.findById(1L);

        verify(ingredienteService).findById(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(ingredienteSimulado.getId(), resultado.getBody().getId());
        assertEquals(ingredienteSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testInclude() {
        IngredienteDto ingredienteDTOSimulado = new IngredienteDto();
        ingredienteDTOSimulado.setNome("Nome do Ingrediente");

        when(ingredienteService.include(ingredienteDTOSimulado)).thenReturn(ingredienteDTOSimulado);

        ResponseEntity<IngredienteDto> resultado = ingredienteController.include(ingredienteDTOSimulado);

        verify(ingredienteService).include(ingredienteDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(ingredienteDTOSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testEdit() {
        IngredienteDto ingredienteDTOSimulado = new IngredienteDto();
        ingredienteDTOSimulado.setId(1L);
        ingredienteDTOSimulado.setNome("Novo Nome do Ingrediente");

        when(ingredienteService.edit(1L, ingredienteDTOSimulado)).thenReturn(ingredienteDTOSimulado);

        ResponseEntity<IngredienteDto> resultado = ingredienteController.edit(1L, ingredienteDTOSimulado);

        verify(ingredienteService).edit(1L, ingredienteDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(ingredienteDTOSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testDelete() {
        IngredienteDto ingredienteDTOSimulado = new IngredienteDto();
        ingredienteDTOSimulado.setId(1L);
        ingredienteDTOSimulado.setNome("Ingrediente Removido");

        when(ingredienteService.delete(1L)).thenReturn(ingredienteDTOSimulado);

        ResponseEntity<IngredienteDto> resultado = ingredienteController.delete(1L);

        verify(ingredienteService).delete(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(ingredienteDTOSimulado.getNome(), resultado.getBody().getNome());
    }
}
