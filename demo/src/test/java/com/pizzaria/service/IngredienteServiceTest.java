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

import main.dto.IngredienteDto;
import main.entity.Ingrediente;
import main.repository.IngredienteRepository;
import main.service.IngredienteService;

@SpringBootTest
class IngredienteServiceTest {

    @InjectMocks
    private IngredienteService ingredienteService;

    @Mock
    private IngredienteRepository ingredienteRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testFindAll() {

    	List<Ingrediente> ingredientesSimulados = new ArrayList<>();
        Ingrediente ingrediente1 = new Ingrediente();
        ingrediente1.setId(1L);
        ingrediente1.setNome("Ingrediente 1");
        ingrediente1.setValor(5.0);
        ingredientesSimulados.add(ingrediente1);

        when(ingredienteRepository.findAll()).thenReturn(ingredientesSimulados);

        List<IngredienteDto> resultado = ingredienteService.findAll();

        verify(ingredienteRepository).findAll();
        assertEquals(ingredientesSimulados.size(), resultado.size());
        assertEquals(ingredientesSimulados.get(0).getNome(), resultado.get(0).getNome());
    }

    @Test
    void testFindById() {

        Ingrediente ingredienteSimulado = new Ingrediente();
        ingredienteSimulado.setId(1L);
        ingredienteSimulado.setNome("Ingrediente 1");
        ingredienteSimulado.setValor(5.0);

        when(ingredienteRepository.findById(1L)).thenReturn(Optional.of(ingredienteSimulado));

        IngredienteDto resultado = ingredienteService.findById(1L);

        verify(ingredienteRepository).findById(1L);
        assertEquals(ingredienteSimulado.getNome(), resultado.getNome());
    }

    @Test
    void testInclude() {

        IngredienteDto ingredienteDTOSimulado = new IngredienteDto();
        ingredienteDTOSimulado.setNome("Ingrediente Teste");
        ingredienteDTOSimulado.setValor(3.5);

        IngredienteDto resultado = ingredienteService.include(ingredienteDTOSimulado);

        verify(ingredienteRepository).save(any(Ingrediente.class));

        assertEquals(ingredienteDTOSimulado.getNome(), resultado.getNome());
        assertEquals(ingredienteDTOSimulado.getValor(), resultado.getValor());
    }

    @Test
    void testEdit() {

        IngredienteDto ingredienteDTOSimulado = new IngredienteDto();
        ingredienteDTOSimulado.setId(1L);
        ingredienteDTOSimulado.setNome("Ingrediente Editado");
        ingredienteDTOSimulado.setValor(4.0);

        when(ingredienteRepository.findById(1L)).thenReturn(Optional.of(new Ingrediente()));

        IngredienteDto resultado = ingredienteService.edit(1L, ingredienteDTOSimulado);

        verify(ingredienteRepository).findById(1L);
        verify(ingredienteRepository).save(any(Ingrediente.class));

        assertEquals(ingredienteDTOSimulado.getId(), resultado.getId());
        assertEquals(ingredienteDTOSimulado.getNome(), resultado.getNome());
        assertEquals(ingredienteDTOSimulado.getValor(), resultado.getValor());
    }

    @Test
    void testDelete() {

        when(ingredienteRepository.findById(1L)).thenReturn(Optional.of(new Ingrediente()));

        IngredienteDto resultado = ingredienteService.delete(1L);

        verify(ingredienteRepository).findById(1L);
        verify(ingredienteRepository).deleteById(1L);

        assertNotNull(resultado);
    }
}

