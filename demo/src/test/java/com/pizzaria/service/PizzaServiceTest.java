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

import main.dto.PizzaDto;
import main.entity.Pizza;
import main.repository.PizzaRepository;
import main.service.PizzaService;

@SpringBootTest
class PizzaServiceTest {

    @InjectMocks
    private PizzaService pizzaService;

    @Mock
    private PizzaRepository pizzaRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testFindAll() {
        List<Pizza> pizzasSimuladas = new ArrayList<>();
        Pizza pizza1 = new Pizza();
        pizza1.setId(1L);
        pizza1.setTamanho(30L);
        pizzasSimuladas.add(pizza1);

        when(pizzaRepository.findAll()).thenReturn(pizzasSimuladas);

        List<PizzaDto> resultado = pizzaService.findAll();

        verify(pizzaRepository).findAll();
        assertEquals(pizzasSimuladas.size(), resultado.size());
        assertEquals(pizzasSimuladas.get(0).getTamanho(), resultado.get(0).getTamanho());
    }

    @Test
    void testFindById() {
        Pizza pizzaSimulada = new Pizza();
        pizzaSimulada.setId(1L);
        pizzaSimulada.setTamanho(40L);

        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizzaSimulada));

        PizzaDto resultado = pizzaService.findById(1L);

        verify(pizzaRepository).findById(1L);
        assertEquals(pizzaSimulada.getTamanho(), resultado.getTamanho());
    }

    @Test
    void testInclude() {
        PizzaDto pizzaDTOSimulado = new PizzaDto();
        pizzaDTOSimulado.setTamanho(50L);

        Pizza resultado = pizzaService.include(pizzaDTOSimulado);

        verify(pizzaRepository).save(any(Pizza.class));
        assertEquals(pizzaDTOSimulado.getTamanho(), resultado.getTamanho());
    }

    @Test
    void testEdit() {
        PizzaDto pizzaDTOSimulado = new PizzaDto();
        pizzaDTOSimulado.setId(1L);
        pizzaDTOSimulado.setTamanho(60L);

        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(new Pizza()));

        PizzaDto resultado = pizzaService.edit(1L, pizzaDTOSimulado);

        verify(pizzaRepository).findById(1L);
        verify(pizzaRepository).save(any(Pizza.class));

        assertEquals(pizzaDTOSimulado.getTamanho(), resultado.getTamanho());
    }

    @Test
    void testDelete() {
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(new Pizza()));

        PizzaDto resultado = pizzaService.delete(1L);

        verify(pizzaRepository).findById(1L);
        verify(pizzaRepository).deleteById(1L);

        assertNotNull(resultado);
    }
}
