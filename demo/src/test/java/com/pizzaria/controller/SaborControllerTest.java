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

import main.controller.SaborController;
import main.dto.SaborDto;
import main.service.SaborService;

@SpringBootTest
class SaborControllerTest {

    @InjectMocks
    private SaborController saborController;

    @Mock
    private SaborService saborService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {
        List<SaborDto> saboresSimulados = new ArrayList<>();
        SaborDto sabor1 = new SaborDto();
        sabor1.setId(1L);
        sabor1.setNome("Sabor 1");
        saboresSimulados.add(sabor1);

        when(saborService.findAll()).thenReturn(saboresSimulados);

        ResponseEntity<List<SaborDto>> resultado = saborController.findAll();

        verify(saborService).findAll();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(saboresSimulados.size(), resultado.getBody().size());
        assertEquals(saboresSimulados.get(0).getNome(), resultado.getBody().get(0).getNome());
    }

    @Test
    void testFindById() {
        SaborDto saborSimulado = new SaborDto();
        saborSimulado.setId(1L);
        saborSimulado.setNome("Sabor 1");

        when(saborService.findById(1L)).thenReturn(saborSimulado);

        ResponseEntity<SaborDto> resultado = saborController.findById(1L);

        verify(saborService).findById(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(saborSimulado.getId(), resultado.getBody().getId());
        assertEquals(saborSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testInclude() {
        SaborDto saborDTOSimulado = new SaborDto();
        saborDTOSimulado.setNome("Sabor Novo");

        when(saborService.include(saborDTOSimulado)).thenReturn(saborDTOSimulado);

        ResponseEntity<SaborDto> resultado = saborController.include(saborDTOSimulado);

        verify(saborService).include(saborDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(saborDTOSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testEdit() {
        SaborDto saborDTOSimulado = new SaborDto();
        saborDTOSimulado.setId(1L);
        saborDTOSimulado.setNome("Sabor Editado");

        when(saborService.edit(1L, saborDTOSimulado)).thenReturn(saborDTOSimulado);

        ResponseEntity<SaborDto> resultado = saborController.edit(1L, saborDTOSimulado);

        verify(saborService).edit(1L, saborDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(saborDTOSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testDelete() {
        SaborDto saborDTOSimulado = new SaborDto();
        saborDTOSimulado.setId(1L);
        saborDTOSimulado.setNome("Sabor 1");

        when(saborService.delete(1L)).thenReturn(saborDTOSimulado);

        ResponseEntity<SaborDto> resultado = saborController.delete(1L);

        verify(saborService).delete(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(saborDTOSimulado.getNome(), resultado.getBody().getNome());
    }
}
