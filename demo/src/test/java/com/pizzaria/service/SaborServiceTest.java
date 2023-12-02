package com.pizzaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.SaborDto;
import main.entity.Sabor;
import main.repository.SaborRepository;
import main.service.SaborService;

@SpringBootTest
class SaborServiceTest {

    @InjectMocks
    private SaborService saborService;

    @Mock
    private SaborRepository saborRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testFindAll() {
        List<Sabor> saboresSimulados = new ArrayList<>();
        Sabor sabor1 = new Sabor();
        sabor1.setId(1L);
        sabor1.setNome("Sabor 1");
        sabor1.setValor(50.0);
        saboresSimulados.add(sabor1);

        when(saborRepository.findAll()).thenReturn(saboresSimulados);

        List<SaborDto> resultado = saborService.findAll();

        assertEquals(saboresSimulados.size(), resultado.size());
        assertEquals(saboresSimulados.get(0).getNome(), resultado.get(0).getNome());
    }

    @Test
    void testFindById() {
        Sabor saborSimulado = new Sabor();
        saborSimulado.setId(1L);
        saborSimulado.setNome("Sabor Simulado");
        saborSimulado.setValor(75.0);

        when(saborRepository.findById(1L)).thenReturn(Optional.of(saborSimulado));

        SaborDto resultado = saborService.findById(1L);

        assertEquals(saborSimulado.getNome(), resultado.getNome());
    }

    @Test
    void testInclude() {
        SaborDto saborDTOSimulado = new SaborDto();
        saborDTOSimulado.setNome("Sabor de Teste");
        saborDTOSimulado.setValor(80.0);

        SaborDto resultado = saborService.include(saborDTOSimulado);

        when(saborRepository.save(any(Sabor.class))).thenReturn(new Sabor());

        assertEquals(saborDTOSimulado.getNome(), resultado.getNome());
        assertEquals(saborDTOSimulado.getValor(), resultado.getValor());
    }

    @Test
    void testEdit() {
        SaborDto saborDTOSimulado = new SaborDto();
        saborDTOSimulado.setId(1L);
        saborDTOSimulado.setNome("Sabor Editado");
        saborDTOSimulado.setValor(90.0);

        when(saborRepository.findById(1L)).thenReturn(Optional.of(new Sabor()));
        when(saborRepository.save(any(Sabor.class))).thenReturn(new Sabor());

        SaborDto resultado = saborService.edit(1L, saborDTOSimulado);

        assertEquals(saborDTOSimulado.getNome(), resultado.getNome());
        assertEquals(saborDTOSimulado.getValor(), resultado.getValor());
    }

    @Test
    void testDelete() {
        when(saborRepository.findById(1L)).thenReturn(Optional.of(new Sabor()));

        SaborDto resultado = saborService.delete(1L);

        assertNotNull(resultado);
    }
}
