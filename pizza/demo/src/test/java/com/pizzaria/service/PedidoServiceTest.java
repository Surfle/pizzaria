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

import main.dto.PedidoDto;
import main.entity.Cliente;
import main.entity.Funcionario;
import main.entity.Pedido;
import main.repository.PedidoRepository;
import main.service.PedidoService;

@SpringBootTest
class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testFindAll() {
        List<Pedido> pedidosSimulados = new ArrayList<>();
        Pedido pedido1 = new Pedido();
        pedido1.setId(1L);
        pedido1.setValor(100.0);
        pedidosSimulados.add(pedido1);

        when(pedidoRepository.findAll()).thenReturn(pedidosSimulados);

        List<PedidoDto> resultado = pedidoService.findAll();

        verify(pedidoRepository).findAll();
        
        assertEquals(pedidosSimulados.size(), resultado.size());
        assertEquals(pedidosSimulados.get(0).getValor(), resultado.get(0).getValor());
    }

    @Test
    void testFindById() {
        Pedido pedidoSimulado = new Pedido();
        pedidoSimulado.setId(1L);
        pedidoSimulado.setValor(50.0);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoSimulado));

        PedidoDto resultado = pedidoService.findById(1L);

        verify(pedidoRepository).findById(1L);
        
        assertEquals(pedidoSimulado.getValor(), resultado.getValor());
    }

    @Test
    void testInclude() {
        PedidoDto pedidoDTOSimulado = new PedidoDto();
        pedidoDTOSimulado.setValor(75.0);
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        pedidoDTOSimulado.setCliente(cliente);
        Funcionario funcionario = new Funcionario();
        funcionario.setId(2L);
        pedidoDTOSimulado.setFuncionario(funcionario);

        PedidoDto resultado = pedidoService.include(pedidoDTOSimulado);

        verify(pedidoRepository).save(any(Pedido.class));
        
        assertEquals(pedidoDTOSimulado.getValor(), resultado.getValor());
        assertEquals(pedidoDTOSimulado.getCliente().getId(), resultado.getCliente().getId());
        assertEquals(pedidoDTOSimulado.getFuncionario().getId(), resultado.getFuncionario().getId());
    }

    @Test
    void testEdit() {
        PedidoDto pedidoDTOSimulado = new PedidoDto();
        pedidoDTOSimulado.setId(1L);
        pedidoDTOSimulado.setValor(80.0);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(new Pedido()));

        PedidoDto resultado = pedidoService.edit(1L, pedidoDTOSimulado);

        verify(pedidoRepository).findById(1L);
        verify(pedidoRepository).save(any(Pedido.class));

        assertEquals(pedidoDTOSimulado.getValor(), resultado.getValor());
    }

    @Test
    void testDelete() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(new Pedido()));

        PedidoDto resultado = pedidoService.delete(1L);

        verify(pedidoRepository).findById(1L);
        verify(pedidoRepository).deleteById(1L);

        assertNotNull(resultado);
    }
}
