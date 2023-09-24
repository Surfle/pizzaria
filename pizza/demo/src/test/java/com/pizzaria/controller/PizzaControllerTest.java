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

import main.controller.PizzaController;
import main.dto.PizzaDto;
import main.service.PizzaService;

@SpringBootTest
class PizzaControllerTest {

    @InjectMocks
    private PizzaController pizzaController;

    @Mock
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {
        List<PizzaDto> pizzasSimuladas = new ArrayList<>();
        PizzaDto pizza1 = new PizzaDto();
        pizza1.setId(1L);
        pizza1.setTamanho(30L);
        pizzasSimuladas.add(pizza1);

        when(pizzaService.findAll()).thenReturn(pizzasSimuladas);

        ResponseEntity<List<PizzaDto>> resultado = pizzaController.findAll();

        verify(pizzaService).findAll();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(pizzasSimuladas.size(), resultado.getBody().size());
        assertEquals(pizzasSimuladas.get(0).getTamanho(), resultado.getBody().get(0).getTamanho());
    }

    @Test
    void testFindById() {
        PizzaDto pizzaSimulada = new PizzaDto();
        pizzaSimulada.setId(1L);
        pizzaSimulada.setTamanho(30L);

        when(pizzaService.findById(1L)).thenReturn(pizzaSimulada);

        ResponseEntity<PizzaDto> resultado = pizzaController.findById(1L);

        verify(pizzaService).findById(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(pizzaSimulada.getId(), resultado.getBody().getId());
        assertEquals(pizzaSimulada.getTamanho(), resultado.getBody().getTamanho());
    }

    @Test
    void testInclude() {
        PizzaDto pizzaDTOSimulada = new PizzaDto();
        pizzaDTOSimulada.setTamanho(30L);

        when(pizzaService.include(pizzaDTOSimulada)).thenReturn(pizzaDTOSimulada);

        ResponseEntity<PizzaDto> resultado = pizzaController.include(pizzaDTOSimulada);

        verify(pizzaService).include(pizzaDTOSimulada);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(pizzaDTOSimulada.getTamanho(), resultado.getBody().getTamanho());
    }

    @Test
    void testEdit() {
        PizzaDto pizzaDTOSimulada = new PizzaDto();
        pizzaDTOSimulada.setId(1L);
        pizzaDTOSimulada.setTamanho(40L);

        when(pizzaService.edit(1L, pizzaDTOSimulada)).thenReturn(pizzaDTOSimulada);

        ResponseEntity<PizzaDto> resultado = pizzaController.edit(1L, pizzaDTOSimulada);

        verify(pizzaService).edit(1L, pizzaDTOSimulada);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(pizzaDTOSimulada.getTamanho(), resultado.getBody().getTamanho());
    }

    @Test
    void testDelete() {
        PizzaDto pizzaDTOSimulada = new PizzaDto();
        pizzaDTOSimulada.setId(1L);
        pizzaDTOSimulada.setTamanho(30L);

        when(pizzaService.delete(1L)).thenReturn(pizzaDTOSimulada);

        ResponseEntity<PizzaDto> resultado = pizzaController.delete(1L);

        verify(pizzaService).delete(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(pizzaDTOSimulada.getTamanho(), resultado.getBody().getTamanho());
    }
}