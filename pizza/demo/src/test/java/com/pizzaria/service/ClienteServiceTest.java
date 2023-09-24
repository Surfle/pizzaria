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

import main.dto.ClienteDto;
import main.entity.Cliente;
import main.repository.ClienteRepository;
import main.service.ClienteService;

@SpringBootTest
class ClienteServiceTest {

	@InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

	@BeforeEach
    public void setUp() {
    }

	@Test
    void testFindById() {

    	Cliente clienteSimulado = new Cliente();
        clienteSimulado.setId(1L);
        clienteSimulado.setNome("Nome");

        ClienteDto clienteDTOSimulado = new ClienteDto();
        clienteDTOSimulado.setId(1L);
        clienteDTOSimulado.setNome("Nome");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteSimulado));

        ClienteDto resultado = clienteService.findById(1L);

        verify(clienteRepository).findById(1L);

        assertEquals(clienteDTOSimulado.getId(), resultado.getId());
        assertEquals(clienteDTOSimulado.getNome(), resultado.getNome());
    }
	
    @Test
    void testFindAll() {

    	List<Cliente> clientesSimulados = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        clientesSimulados.add(cliente1);

        when(clienteRepository.findAll()).thenReturn(clientesSimulados);

        List<ClienteDto> resultado = clienteService.findAll();

        verify(clienteRepository).findAll();

        assertEquals(clientesSimulados.size(), resultado.size());
        assertEquals(clientesSimulados.get(0).getNome(), resultado.get(0).getNome());
    }
    
    @Test
    void testInclude() {
    	
        ClienteDto clienteDTOSimulado = new ClienteDto();
        clienteDTOSimulado.setNome("Nome do Cliente");

        ClienteDto resultado = clienteService.include(clienteDTOSimulado);

        verify(clienteRepository).save(any(Cliente.class));

        assertEquals(clienteDTOSimulado.getNome(), resultado.getNome());
    }

    @Test
    void testEdit() {
        ClienteDto clienteDTOSimulado = new ClienteDto();
        clienteDTOSimulado.setId(1L);
        clienteDTOSimulado.setNome("Novo Nome do Cliente");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));

        ClienteDto resultado = clienteService.edit(1L, clienteDTOSimulado);

        verify(clienteRepository).findById(1L);

        verify(clienteRepository).save(any(Cliente.class));

        assertEquals(clienteDTOSimulado.getNome(), resultado.getNome());
    }

    @Test
    void testDelete() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));

        ClienteDto resultado = clienteService.delete(1L);

        verify(clienteRepository).findById(1L);

        verify(clienteRepository).deleteById(1L);

        assertNotNull(resultado);
    }
    
}
